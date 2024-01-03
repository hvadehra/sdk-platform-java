/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.showcase.v1beta1.it;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.FixedExecutorProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.CancelledException;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.protobuf.Duration;
import com.google.rpc.Status;
import com.google.showcase.v1beta1.BlockRequest;
import com.google.showcase.v1beta1.BlockResponse;
import com.google.showcase.v1beta1.EchoClient;
import com.google.showcase.v1beta1.EchoRequest;
import com.google.showcase.v1beta1.EchoResponse;
import com.google.showcase.v1beta1.EchoSettings;
import com.google.showcase.v1beta1.it.util.TestClientInitializer;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.showcase.v1beta1.stub.EchoStubSettings;
import io.grpc.ManagedChannelBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ITUnaryCallable {

  private static EchoClient grpcClient;
  private static EchoClient grpcClientMultithreaded;

  private static EchoClient grpcVTClient;


  private static EchoClient httpjsonClient;
  private static EchoClient httpjsonVTClient;

  private static final ScheduledExecutorService EXECUTOR = new ScheduledThreadPoolExecutor(100);

  @BeforeClass
  public static void createClients() throws Exception {
    // Create gRPC Echo Client
    grpcClient = TestClientInitializer.createGrpcEchoClient();

    // Create Http JSON Echo Client
    httpjsonClient = TestClientInitializer.createHttpJsonEchoClient();
  }

  public void createHttpJsonVTClient() throws Exception {
    // Set background executor provider. InstantiatingExecutorProvider only has settings for thread pool count.
    // Set response timeout to 7 seconds.
    RetrySettings defaultNoRetrySettings =
            RetrySettings.newBuilder()
                    .setInitialRpcTimeout(org.threeten.bp.Duration.ofSeconds(2))
                    .setRpcTimeoutMultiplier(1.0)
                    .setMaxRpcTimeout(org.threeten.bp.Duration.ofSeconds(2))
                    .setTotalTimeout(org.threeten.bp.Duration.ofSeconds(2))
                    // Explicitly set retries as disabled (maxAttempts == 1)
                    .setMaxAttempts(1)
                    .build();
    EchoStubSettings.Builder httpJsonEchoSettingsBuilder = EchoStubSettings.newHttpJsonBuilder();
    httpJsonEchoSettingsBuilder
            .blockSettings()
            .setRetrySettings(defaultNoRetrySettings)
            .setRetryableCodes(ImmutableSet.of(StatusCode.Code.DEADLINE_EXCEEDED));
    EchoSettings httpJsonEchoSettings = EchoSettings.create(httpJsonEchoSettingsBuilder.build());
    httpJsonEchoSettings =
            httpJsonEchoSettings
                    .toBuilder()
                    .setCredentialsProvider(NoCredentialsProvider.create())
                    .setTransportChannelProvider(
                            EchoSettings.defaultHttpJsonTransportProviderBuilder()
                                    .setExecutor(Executors.newVirtualThreadPerTaskExecutor())
                                    .setHttpTransport(
                                            new NetHttpTransport.Builder().doNotValidateCertificate().build())
                                    .setEndpoint("http://localhost:7469")
                                    .build())
                    .build();
    httpjsonVTClient = EchoClient.create(httpJsonEchoSettings);
  }

  public void createGrpcClientForConcurrency() throws Exception {
    // Set background executor provider. InstantiatingExecutorProvider only has settings for thread pool count.
    // Set response timeout to 20 seconds.
    RetrySettings defaultNoRetrySettings =
            RetrySettings.newBuilder()
                    .setInitialRpcTimeout(org.threeten.bp.Duration.ofSeconds(200))
                    .setRpcTimeoutMultiplier(1.0)
                    .setMaxRpcTimeout(org.threeten.bp.Duration.ofSeconds(200))
                    .setTotalTimeout(org.threeten.bp.Duration.ofSeconds(200))
                    // Explicitly set retries as disabled (maxAttempts == 1)
                    .setMaxAttempts(1)
                    .build();
    EchoStubSettings.Builder grpcEchoSettingsBuilder = EchoStubSettings.newBuilder();
    grpcEchoSettingsBuilder
            .blockSettings()
            .setRetrySettings(defaultNoRetrySettings)
            .setRetryableCodes(ImmutableSet.of());
    EchoSettings grpcEchoSettings = EchoSettings.create(grpcEchoSettingsBuilder.build());
    grpcEchoSettings =
            grpcEchoSettings
                    .toBuilder()
                    .setCredentialsProvider(NoCredentialsProvider.create())
                    .setTransportChannelProvider(
                            EchoSettings.defaultGrpcTransportProviderBuilder()
                                    .setExecutor(EXECUTOR)
                                    .setChannelConfigurator(ManagedChannelBuilder::usePlaintext)
                                    .setInterceptorProvider(() -> ImmutableList.of()).build())
                    .build();
    grpcClientMultithreaded = EchoClient.create(grpcEchoSettings);
  }


    public void createVTClient() throws Exception {
    // Set background executor provider. InstantiatingExecutorProvider only has settings for thread pool count.
    // Set response timeout to 10 seconds.
    RetrySettings defaultNoRetrySettings =
            RetrySettings.newBuilder()
                    .setInitialRpcTimeout(org.threeten.bp.Duration.ofSeconds(10))
                    .setRpcTimeoutMultiplier(1.0)
                    .setMaxRpcTimeout(org.threeten.bp.Duration.ofSeconds(10))
                    .setTotalTimeout(org.threeten.bp.Duration.ofSeconds(10))
                    // Explicitly set retries as disabled (maxAttempts == 1)
                    .setMaxAttempts(1)
                    .build();
    EchoStubSettings.Builder grpcEchoSettingsBuilder = EchoStubSettings.newBuilder();
    grpcEchoSettingsBuilder
            .blockSettings()
            .setRetrySettings(defaultNoRetrySettings)
            .setRetryableCodes(ImmutableSet.of());
    EchoSettings grpcEchoSettings = EchoSettings.create(grpcEchoSettingsBuilder.build());
    grpcEchoSettings =
            grpcEchoSettings
                    .toBuilder()
                    .setCredentialsProvider(NoCredentialsProvider.create())
                    .setTransportChannelProvider(
                            EchoSettings.defaultGrpcTransportProviderBuilder()
                                    .setExecutor(Executors.newVirtualThreadPerTaskExecutor())
                                    .setChannelConfigurator(ManagedChannelBuilder::usePlaintext)
                                    .setInterceptorProvider(() -> ImmutableList.of()).build())
                    .build();
    grpcVTClient = EchoClient.create(grpcEchoSettings);


  }

  @AfterClass
  public static void destroyClients() throws InterruptedException {
    grpcClient.close();
    httpjsonClient.close();

    grpcClient.awaitTermination(TestClientInitializer.AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
    httpjsonClient.awaitTermination(
        TestClientInitializer.AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
  }

  @Test
  public void testGrpc_receiveContent() {
    assertThat(echoGrpc("grpc-echo?")).isEqualTo("grpc-echo?");
    assertThat(echoGrpc("grpc-echo!")).isEqualTo("grpc-echo!");
  }

  @Test
  public void testGrpcMultiThreaded_receiveContent() throws Exception {
    createGrpcClientForConcurrency();
    List<Future<BlockResponse>> results = new ArrayList<>();
    // 100 concurrent echo calls hangs but 90 works fine.
    for (int i = 0; i < 90; i++) {
      String value = "echo response - " + i;
      Callable<BlockResponse> echoTask =
          () ->
              grpcClientMultithreaded.block(
                  BlockRequest.newBuilder()
                      .setResponseDelay(Duration.newBuilder().setSeconds(2).build())
                      .setSuccess(BlockResponse.newBuilder().setContent(value + " thread name: " + Thread.currentThread().getName()))
                      .build());
      results.add(EXECUTOR.submit(echoTask));
    }

    // Receive response
    for (Future<BlockResponse> result : results) {
      BlockResponse response = result.get();
      System.out.println(response.getContent());
    }
    EXECUTOR.shutdown();
  }

  @Test
  public void testVTGrpc_receiveContent_delayedResponse() throws Exception {
    createVTClient();
    assertThat(echoGrpcVirtualThread_delayedResponse("grpc-echo?")).isEqualTo("grpc-echo?");
    assertThat(echoGrpcVirtualThread_delayedResponse("grpc-echo!")).isEqualTo("grpc-echo!");
  }

  @Test
  public void testVTHttpJson_receiveContent_delayedResponse() throws Exception {
    createHttpJsonVTClient();
    assertThat(echoHttpJsonVirtualThread_delayedResponse("http-echo?")).isEqualTo("http-echo?");
    assertThat(echoHttpJsonVirtualThread_delayedResponse("http-echo!")).isEqualTo("http-echo!");
  }

  @Test
  public void testGrpc_serverResponseError_throwsException() {
    Status cancelledStatus =
        Status.newBuilder().setCode(StatusCode.Code.CANCELLED.ordinal()).build();
    EchoRequest requestWithServerError = EchoRequest.newBuilder().setError(cancelledStatus).build();
    CancelledException exception =
        assertThrows(CancelledException.class, () -> grpcClient.echo(requestWithServerError));
    assertThat(exception.getStatusCode().getCode()).isEqualTo(GrpcStatusCode.Code.CANCELLED);
  }

  @Test
  public void testHttpJson_receiveContent() {
    assertThat(echoHttpJson("http-echo?")).isEqualTo("http-echo?");
    assertThat(echoHttpJson("http-echo!")).isEqualTo("http-echo!");
  }

  @Test
  public void testHttpJson_serverResponseError_throwsException() {
    EchoRequest requestWithServerError =
        EchoRequest.newBuilder()
            .setError(Status.newBuilder().setCode(StatusCode.Code.CANCELLED.ordinal()).build())
            .build();
    CancelledException exception =
        assertThrows(CancelledException.class, () -> httpjsonClient.echo(requestWithServerError));
    assertThat(exception.getStatusCode().getCode()).isEqualTo(StatusCode.Code.CANCELLED);
  }

  private String echoGrpc(String value) {
    EchoResponse response = grpcClient.echo(EchoRequest.newBuilder().setContent(value).build());
    return response.getContent();
  }

  private String echoGrpcVirtualThread_delayedResponse(String value) {
    // Set response delay to 6 seconds.
    BlockResponse response = grpcVTClient.block(BlockRequest.newBuilder().setResponseDelay(Duration.newBuilder().setSeconds(6).build()).setSuccess(BlockResponse.newBuilder().setContent(value)).build());
    return response.getContent();
  }


  private String echoHttpJson(String value) {
    EchoResponse response = httpjsonClient.echo(EchoRequest.newBuilder().setContent(value).build());
    return response.getContent();
  }

  private String echoHttpJsonVirtualThread_delayedResponse(String value) {
    BlockResponse response = httpjsonVTClient.block(BlockRequest.newBuilder().setResponseDelay(Duration.newBuilder().setSeconds(15).build()).setSuccess(BlockResponse.newBuilder().setContent(value)).build());
    return response.getContent();
  }
}
