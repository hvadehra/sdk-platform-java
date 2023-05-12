/*
 * Copyright 2017 Google LLC
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google LLC nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.google.api.gax.httpjson;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/** Implementation of HttpJsonChannel which can issue http-json calls. */
@BetaApi
public class ManagedHttpJsonChannel implements HttpJsonChannel, BackgroundResource {

  private final Executor executor;
  private final boolean usingDefaultExecutor;
  private final String endpoint;
  private final HttpTransport httpTransport;
  private final ScheduledExecutorService deadlineScheduledExecutorService;
  private boolean isTransportShutdown;

  protected ManagedHttpJsonChannel() {
    this(null, true, null, null);
  }

  private ManagedHttpJsonChannel(
      Executor executor,
      boolean usingDefaultExecutor,
      String endpoint,
      @Nullable HttpTransport httpTransport) {
    this.executor = executor;
    this.usingDefaultExecutor = usingDefaultExecutor;
    this.endpoint = endpoint;
    this.httpTransport = httpTransport == null ? new NetHttpTransport() : httpTransport;
    this.deadlineScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
  }

  @Override
  public <RequestT, ResponseT> HttpJsonClientCall<RequestT, ResponseT> newCall(
      ApiMethodDescriptor<RequestT, ResponseT> methodDescriptor, HttpJsonCallOptions callOptions) {

    return new HttpJsonClientCallImpl<>(
        methodDescriptor,
        endpoint,
        callOptions,
        httpTransport,
        executor,
        deadlineScheduledExecutorService);
  }

  @Override
  public synchronized void shutdown() {
    // Calling shutdown() twice should no-op
    if (isTransportShutdown) {
      return;
    }
    try {
      // Only shutdown the executor if it was created by Gax. External executors
      // should be managed by the user.
      if (usingDefaultExecutor) {
        ((ExecutorService) executor).shutdown();
      }
      deadlineScheduledExecutorService.shutdown();
      httpTransport.shutdown();
      isTransportShutdown = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean isShutdown() {
    if (usingDefaultExecutor) {
      return ((ExecutorService) executor).isShutdown()
          && deadlineScheduledExecutorService.isShutdown();
    }
    return deadlineScheduledExecutorService.isShutdown();
  }

  @Override
  public boolean isTerminated() {
    if (usingDefaultExecutor) {
      return ((ExecutorService) executor).isTerminated()
          && deadlineScheduledExecutorService.isTerminated();
    }
    return deadlineScheduledExecutorService.isTerminated();
  }

  @Override
  public void shutdownNow() {
    shutdown();
  }

  @Override
  public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
    long endTimeNanos = System.nanoTime() + unit.toNanos(duration);
    long awaitTimeNanos = endTimeNanos - System.nanoTime();
    // Only awaitTermination for the executor if it was created by Gax. External executors
    // should be managed by the user.
    if (usingDefaultExecutor && awaitTimeNanos > 0) {
      boolean terminated = ((ExecutorService) executor).awaitTermination(awaitTimeNanos, unit);
      if (!terminated) {
        return false;
      }
    }
    awaitTimeNanos = endTimeNanos - System.nanoTime();
    return deadlineScheduledExecutorService.awaitTermination(awaitTimeNanos, unit);
  }

  @Override
  public void close() {
    shutdown();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private Executor executor;
    private String endpoint;
    private HttpTransport httpTransport;

    private Builder() {}

    public Builder setExecutor(Executor executor) {
      this.executor = executor;
      return this;
    }

    public Builder setEndpoint(String endpoint) {
      this.endpoint = endpoint;
      return this;
    }

    public Builder setHttpTransport(HttpTransport httpTransport) {
      this.httpTransport = httpTransport;
      return this;
    }

    public ManagedHttpJsonChannel build() {
      Preconditions.checkNotNull(endpoint);

      boolean usingDefaultExecutor = executor == null;
      if (usingDefaultExecutor) {
        executor = InstantiatingExecutorProvider.newBuilder().build().getExecutor();
      }

      return new ManagedHttpJsonChannel(
          executor,
          usingDefaultExecutor,
          endpoint,
          httpTransport == null ? new NetHttpTransport() : httpTransport);
    }
  }
}
