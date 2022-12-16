/*
 * Copyright 2019 Google LLC
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
package com.google.api.gax.tracing;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.CancelledException;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.StatusCode.Code;
import com.google.api.gax.rpc.testing.FakeCallContext;
import com.google.api.gax.rpc.testing.FakeStatusCode;
import com.google.api.gax.tracing.ApiTracerFactory.OperationType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

@RunWith(JUnit4.class)
public class TracedClientStreamingCallableTest {
  private static final SpanName SPAN_NAME = SpanName.of("fake-client", "fake-method");
  public @Rule MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

  @Mock private ApiTracerFactory tracerFactory;
  private ApiTracer parentTracer = BaseApiTracer.getInstance();
  @Mock private ApiTracer tracer;

  private FakeClientCallable innerCallable;
  private TracedClientStreamingCallable<String, String> tracedCallable;
  private FakeStreamObserver outerResponseObsever;
  private FakeCallContext callContext;

  @Before
  public void setUp() {
    when(tracerFactory.newTracer(parentTracer, SPAN_NAME, OperationType.ClientStreaming))
        .thenReturn(tracer);
    innerCallable = new FakeClientCallable();
    tracedCallable = new TracedClientStreamingCallable<>(innerCallable, tracerFactory, SPAN_NAME);
    outerResponseObsever = new FakeStreamObserver();
    callContext = FakeCallContext.createDefault();
  }

  @Test
  public void testTracerCreated() {
    tracedCallable.clientStreamingCall(outerResponseObsever, callContext);

    verify(tracerFactory, times(1))
        .newTracer(parentTracer, SPAN_NAME, OperationType.ClientStreaming);
  }

  @Test
  public void testCallContextPropagated() {
    ImmutableMap<String, List<String>> extraHeaders =
        ImmutableMap.<String, List<String>>of("header1", ImmutableList.of("value1"));

    ApiCallContext newCallContext = callContext.withExtraHeaders(extraHeaders);
    tracedCallable.clientStreamingCall(outerResponseObsever, newCallContext);

    assertThat(innerCallable.callContext.getExtraHeaders()).isEqualTo(extraHeaders);
  }

  @Test
  public void testOperationCancelled() {
    ApiStreamObserver<String> clientStream =
        tracedCallable.clientStreamingCall(outerResponseObsever, callContext);

    clientStream.onError(new CancellationException("explicitly cancelled"));
    innerCallable.responseObserver.onError(
        new CancelledException(
            "fake exception that would be generated by a client cancelling the rpc",
            null,
            FakeStatusCode.of(Code.CANCELLED),
            false));

    verify(tracer, times(1)).operationCancelled();
  }

  @Test
  public void testOperationFinished() {
    tracedCallable.clientStreamingCall(outerResponseObsever, callContext);
    innerCallable.responseObserver.onNext("ignored");
    innerCallable.responseObserver.onCompleted();

    verify(tracer, times(1)).operationSucceeded();
  }

  @Test
  public void testOperationFailed() {
    RuntimeException expectedError = new RuntimeException("fake error");
    tracedCallable.clientStreamingCall(outerResponseObsever, callContext);
    innerCallable.responseObserver.onError(expectedError);

    verify(tracer, times(1)).operationFailed(expectedError);
  }

  @Test
  public void testSyncError() {
    RuntimeException expectedError = new RuntimeException("fake error");
    innerCallable.syncError = expectedError;

    try {
      tracedCallable.clientStreamingCall(outerResponseObsever, callContext);
    } catch (RuntimeException e) {
      // noop
    }

    verify(tracer, times(1)).operationFailed(expectedError);
  }

  @Test
  public void testRequestNotify() {
    ApiStreamObserver<String> requestStream =
        tracedCallable.clientStreamingCall(outerResponseObsever, callContext);

    requestStream.onNext("request1");
    requestStream.onNext("request2");
    innerCallable.responseObserver.onNext("response");
    innerCallable.responseObserver.onCompleted();

    verify(tracer, times(2)).requestSent();
    assertThat(outerResponseObsever.completed).isTrue();
    assertThat(innerCallable.requestObserver.messages).containsExactly("request1", "request2");
  }

  private static class FakeClientCallable extends ClientStreamingCallable<String, String> {
    private RuntimeException syncError;
    private ApiStreamObserver<String> responseObserver;
    // TODO: https://github.com/googleapis/gax-java/issues/687
    private ApiCallContext callContext;
    private FakeStreamObserver requestObserver;

    @Override
    public ApiStreamObserver<String> clientStreamingCall(
        ApiStreamObserver<String> responseObserver, ApiCallContext context) {

      if (syncError != null) {
        throw syncError;
      }

      this.responseObserver = responseObserver;
      this.callContext = context;
      this.requestObserver = new FakeStreamObserver();

      return this.requestObserver;
    }
  }

  private static class FakeStreamObserver implements ApiStreamObserver<String> {
    private List<String> messages = Lists.newArrayList();
    private Throwable error;
    private boolean completed;

    @Override
    public void onNext(String value) {
      messages.add(value);
    }

    @Override
    public void onError(Throwable t) {
      error = t;
      completed = true;
    }

    @Override
    public void onCompleted() {
      completed = true;
    }
  }
}
