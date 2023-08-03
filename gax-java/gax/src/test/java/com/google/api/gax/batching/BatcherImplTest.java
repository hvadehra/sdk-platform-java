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
package com.google.api.gax.batching;

import static com.google.api.gax.rpc.testing.FakeBatchableApi.SQUARER_BATCHING_DESC_V2;
import static com.google.api.gax.rpc.testing.FakeBatchableApi.callLabeledIntSquarer;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.mockito.Mockito.when;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.SettableApiFuture;
import com.google.api.gax.batching.BatcherImpl.BatcherReference;
import com.google.api.gax.batching.FlowController.FlowControlRuntimeException;
import com.google.api.gax.batching.FlowController.LimitExceededBehavior;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.api.gax.rpc.testing.FakeBatchableApi.LabeledIntList;
import com.google.api.gax.rpc.testing.FakeBatchableApi.LabeledIntSquarerCallable;
import com.google.api.gax.rpc.testing.FakeBatchableApi.SquarerBatchingDescriptorV2;
import com.google.api.gax.rpc.testing.FakeCallContext;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Queues;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class BatcherImplTest {

  private static final ScheduledExecutorService EXECUTOR =
      Executors.newSingleThreadScheduledExecutor();

  private Batcher<Integer, Integer> underTest;
  private final LabeledIntList labeledIntList = new LabeledIntList("Default");
  private final BatchingSettings batchingSettings =
      BatchingSettings.newBuilder()
          .setElementCountThreshold(1000L)
          .setRequestByteThreshold(1000L)
          .setDelayThreshold(java.time.Duration.ofSeconds(1000))
          .build();

  @After
  public void tearDown() throws InterruptedException {
    if (underTest != null) {
      try {
        // Close the batcher to avoid warnings of orphaned batchers
        underTest.close();
      } catch (BatchingException ignored) {
        // Some tests intentionally inject failures into mutations
      }
    }
  }

  @AfterClass
  public static void tearDownExecutor() throws InterruptedException {
    EXECUTOR.shutdown();
    EXECUTOR.awaitTermination(100, TimeUnit.MILLISECONDS);
  }
  /** The accumulated results in the test are resolved when {@link Batcher#flush()} is called. */
  @Test
  public void testResultsAreResolvedAfterFlush() throws Exception {
    underTest = createDefaultBatcherImpl(batchingSettings, null);
    Future<Integer> result = underTest.add(4);
    assertThat(result.isDone()).isFalse();
    underTest.flush();
    assertThat(result.isDone()).isTrue();
    assertThat(result.get()).isEqualTo(16);

    Future<Integer> anotherResult = underTest.add(5);
    assertThat(anotherResult.isDone()).isFalse();
  }

  @Test
  public void testSendOutstanding() {
    final AtomicInteger callableCounter = new AtomicInteger();

    underTest =
        new BatcherImpl<>(
            SQUARER_BATCHING_DESC_V2,
            new LabeledIntSquarerCallable() {
              @Override
              public ApiFuture<List<Integer>> futureCall(
                  LabeledIntList request, ApiCallContext context) {
                callableCounter.incrementAndGet();
                return super.futureCall(request, context);
              }
            },
            labeledIntList,
            batchingSettings,
            EXECUTOR);

    // Empty Batcher
    underTest.sendOutstanding();
    assertThat(callableCounter.get()).isEqualTo(0);

    underTest.add(2);
    underTest.add(3);
    underTest.add(4);
    underTest.sendOutstanding();
    assertThat(callableCounter.get()).isEqualTo(1);
  }

  /** Element results are resolved after batch is closed. */
  @Test
  public void testWhenBatcherIsClose() throws Exception {
    Future<Integer> result;
    try (Batcher<Integer, Integer> batcher = createDefaultBatcherImpl(batchingSettings, null)) {
      result = batcher.add(5);
    }
    assertThat(result.isDone()).isTrue();
    assertThat(result.get()).isEqualTo(25);
  }

  /** Validates exception when batch is called after {@link Batcher#close()}. */
  @Test
  public void testNoElementAdditionAfterClose() throws Exception {
    underTest = createDefaultBatcherImpl(batchingSettings, null);
    underTest.close();
    Throwable addOnClosedError = null;
    try {
      underTest.add(1);
    } catch (Exception ex) {
      addOnClosedError = ex;
    }
    assertThat(addOnClosedError).isInstanceOf(IllegalStateException.class);
    assertThat(addOnClosedError)
        .hasMessageThat()
        .matches("Cannot add elements on a closed batcher");
  }

  /** Validates exception when batch is called after {@link Batcher#close()}. */
  @Test
  public void testNoElementAdditionAfterCloseAsync() throws Exception {
    underTest = createDefaultBatcherImpl(batchingSettings, null);
    underTest.add(1);
    underTest.closeAsync();

    IllegalStateException e =
        Assert.assertThrows(
            IllegalStateException.class,
            new ThrowingRunnable() {
              @Override
              public void run() throws Throwable {
                underTest.add(1);
              }
            });

    assertThat(e).hasMessageThat().matches("Cannot add elements on a closed batcher");
  }

  @Test
  public void testCloseAsyncNonblocking() throws ExecutionException, InterruptedException {
    final SettableApiFuture<List<Integer>> innerFuture = SettableApiFuture.create();

    UnaryCallable<LabeledIntList, List<Integer>> unaryCallable =
        new UnaryCallable<LabeledIntList, List<Integer>>() {
          @Override
          public ApiFuture<List<Integer>> futureCall(
              LabeledIntList request, ApiCallContext context) {
            return innerFuture;
          }
        };
    underTest =
        new BatcherImpl<>(
            SQUARER_BATCHING_DESC_V2, unaryCallable, labeledIntList, batchingSettings, EXECUTOR);

    ApiFuture<Integer> elementFuture = underTest.add(1);

    Stopwatch stopwatch = Stopwatch.createStarted();
    ApiFuture<Void> closeFuture = underTest.closeAsync();
    assertThat(stopwatch.elapsed(TimeUnit.MILLISECONDS)).isAtMost(100);

    assertThat(closeFuture.isDone()).isFalse();
    assertThat(elementFuture.isDone()).isFalse();

    innerFuture.set(ImmutableList.of(1));
    closeFuture.get();
  }

  /** Verifies exception occurred at RPC is propagated to element results */
  @Test
  public void testResultFailureAfterRPCFailure() throws Exception {
    final Exception fakeError = new RuntimeException();
    UnaryCallable<LabeledIntList, List<Integer>> unaryCallable =
        new UnaryCallable<LabeledIntList, List<Integer>>() {
          @Override
          public ApiFuture<List<Integer>> futureCall(
              LabeledIntList request, ApiCallContext context) {
            return ApiFutures.immediateFailedFuture(fakeError);
          }
        };
    underTest =
        new BatcherImpl<>(
            SQUARER_BATCHING_DESC_V2, unaryCallable, labeledIntList, batchingSettings, EXECUTOR);
    Future<Integer> failedResult = underTest.add(5);
    underTest.add(6);
    underTest.add(7);
    underTest.flush();
    assertThat(failedResult.isDone()).isTrue();
    Throwable actualError = null;
    try {
      failedResult.get();
    } catch (InterruptedException | ExecutionException ex) {
      actualError = ex;
    }
    assertThat(actualError).hasCauseThat().isSameInstanceAs(fakeError);

    try {
      underTest.close();
    } catch (RuntimeException e) {
      actualError = e;
    }

    assertThat(actualError).isNotNull();
    assertThat(actualError).isInstanceOf(BatchingException.class);
    assertThat(actualError)
        .hasMessageThat()
        .contains("1 batches failed to apply due to: 1 RuntimeException");
  }

  /** Resolves future results when {@link BatchingDescriptor#splitResponse} throws exception. */
  @Test
  public void testExceptionInDescriptor() throws InterruptedException {
    final RuntimeException fakeError = new RuntimeException("internal exception");
    BatchingDescriptor<Integer, Integer, LabeledIntList, List<Integer>> descriptor =
        new SquarerBatchingDescriptorV2() {
          @Override
          public void splitResponse(
              List<Integer> batchResponse, List<BatchEntry<Integer, Integer>> batch) {
            throw fakeError;
          }
        };
    underTest =
        new BatcherImpl<>(
            descriptor, callLabeledIntSquarer, labeledIntList, batchingSettings, EXECUTOR);

    Future<Integer> result = underTest.add(2);
    underTest.flush();
    Throwable actualError = null;
    try {
      result.get();
    } catch (ExecutionException ex) {
      actualError = ex;
    }

    assertThat(actualError).hasCauseThat().isSameInstanceAs(fakeError);
    try {
      underTest.close();
    } catch (Exception batchingEx) {
      actualError = batchingEx;
    }
    assertThat(actualError).isInstanceOf(BatchingException.class);
    assertThat(actualError)
        .hasMessageThat()
        .contains(
            "Batching finished with 1 batches failed to apply due to: 1 RuntimeException and 0 "
                + "partial failures.");
  }

  /** Resolves future results when {@link BatchingDescriptor#splitException} throws exception */
  @Test
  public void testExceptionInDescriptorErrorHandling() throws InterruptedException {
    final RuntimeException fakeError = new RuntimeException("internal exception");
    BatchingDescriptor<Integer, Integer, LabeledIntList, List<Integer>> descriptor =
        new SquarerBatchingDescriptorV2() {
          @Override
          public void splitResponse(
              List<Integer> batchResponse, List<BatchEntry<Integer, Integer>> batch) {
            throw fakeError;
          }

          @Override
          public void splitException(
              Throwable throwable, List<BatchEntry<Integer, Integer>> batch) {
            throw fakeError;
          }
        };
    underTest =
        new BatcherImpl<>(
            descriptor, callLabeledIntSquarer, labeledIntList, batchingSettings, EXECUTOR);

    Future<Integer> result = underTest.add(2);
    underTest.flush();
    Throwable actualError = null;
    try {
      result.get();
    } catch (ExecutionException ex) {
      actualError = ex;
    }

    assertThat(actualError).hasCauseThat().isSameInstanceAs(fakeError);
    try {
      underTest.close();
    } catch (Exception ex) {
      actualError = ex;
    }
    assertThat(actualError).isInstanceOf(BatchingException.class);
  }

  @Test
  public void testWhenElementCountExceeds() throws Exception {
    BatchingSettings settings = batchingSettings.toBuilder().setElementCountThreshold(2L).build();
    testElementTriggers(settings);
  }

  @Test
  public void testWhenElementBytesExceeds() throws Exception {
    BatchingSettings settings = batchingSettings.toBuilder().setRequestByteThreshold(2L).build();
    testElementTriggers(settings);
  }

  @Test
  public void testWhenThresholdIsDisabled() throws Exception {
    BatchingSettings settings =
        BatchingSettings.newBuilder()
            .setElementCountThreshold(null)
            .setRequestByteThreshold(null)
            .setDelayThreshold((java.time.Duration) null)
            .build();
    underTest = createDefaultBatcherImpl(settings, null);
    Future<Integer> result = underTest.add(2);
    underTest.add(3);
    assertThat(result.isDone()).isTrue();
    assertThat(result.get()).isEqualTo(4);
  }

  @Test
  public void testWhenDelayThresholdExceeds() throws Exception {
    BatchingSettings settings =
        batchingSettings.toBuilder().setDelayThreshold(java.time.Duration.ofMillis(100)).build();
    underTest = createDefaultBatcherImpl(settings, null);
    Future<Integer> result = underTest.add(6);
    assertThat(result.isDone()).isFalse();
    assertThat(result.get()).isEqualTo(36);
  }

  /** Validates that the elements are not leaking to multiple batches */
  @Test(timeout = 500)
  public void testElementsNotLeaking() throws Exception {
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ScheduledExecutorService multiThreadExecutor = Executors.newScheduledThreadPool(20);

    final AtomicBoolean isDuplicateElement = new AtomicBoolean(false);
    final ConcurrentMap<Integer, Boolean> map = new ConcurrentHashMap<>();
    final UnaryCallable<LabeledIntList, List<Integer>> callable =
        new UnaryCallable<LabeledIntList, List<Integer>>() {
          @Override
          public ApiFuture<List<Integer>> futureCall(
              LabeledIntList request, ApiCallContext context) {
            for (int val : request.ints) {
              Boolean isPresent = map.putIfAbsent(val, Boolean.TRUE);
              if (isPresent != null && isPresent) {
                isDuplicateElement.set(true);
                throw new AssertionError("Duplicate Element found");
              }
            }
            return ApiFutures.immediateFuture(request.ints);
          }
        };
    BatchingSettings settings =
        batchingSettings.toBuilder().setDelayThreshold(java.time.Duration.ofMillis(50)).build();

    try (final BatcherImpl<Integer, Integer, LabeledIntList, List<Integer>> batcherTest =
        new BatcherImpl<>(SQUARER_BATCHING_DESC_V2, callable, labeledIntList, settings, EXECUTOR)) {

      final Callable<Void> addElement =
          new Callable<Void>() {
            @Override
            public Void call() throws Exception {
              int counter = 0;
              while (!isDuplicateElement.get() && counter < 10_000) {
                batcherTest.add(counter++);
              }
              return null;
            }
          };
      final Callable<Void> sendBatch =
          new Callable<Void>() {
            @Override
            public Void call() throws InterruptedException {
              batcherTest.flush();
              return null;
            }
          };

      // Started sequential element addition
      Future<Void> future = singleThreadExecutor.submit(addElement);
      for (int i = 0; !isDuplicateElement.get() && i < 3_000; i++) {
        multiThreadExecutor.submit(sendBatch);
      }

      // Closing the resources
      future.get();
      assertThat(isDuplicateElement.get()).isFalse();
      singleThreadExecutor.shutdown();
      multiThreadExecutor.shutdown();
    }
  }

  /** Validates ongoing runnable is cancelled once Batcher is GCed. */
  @Test
  public void testPushCurrentBatchRunnable() throws Exception {
    long DELAY_TIME = 50L;
    BatchingSettings settings =
        batchingSettings
            .toBuilder()
            .setDelayThreshold(java.time.Duration.ofMillis(DELAY_TIME))
            .build();
    BatcherImpl<Integer, Integer, LabeledIntList, List<Integer>> batcher =
        createDefaultBatcherImpl(settings, null);

    BatcherImpl.PushCurrentBatchRunnable<Integer, Integer, LabeledIntList, List<Integer>>
        pushBatchRunnable = new BatcherImpl.PushCurrentBatchRunnable<>(batcher);
    ScheduledFuture<?> onGoingRunnable =
        EXECUTOR.scheduleWithFixedDelay(
            pushBatchRunnable, DELAY_TIME, DELAY_TIME, TimeUnit.MILLISECONDS);
    pushBatchRunnable.setScheduledFuture(onGoingRunnable);

    boolean isExecutorCancelled = pushBatchRunnable.isCancelled();

    // ScheduledFuture should be not isCancelled yet.
    assertThat(isExecutorCancelled).isFalse();

    // Batcher present inside runnable should be GCed after following loop.
    batcher.close();
    batcher = null;
    for (int retry = 0; retry < 3; retry++) {
      System.gc();
      System.runFinalization();
      isExecutorCancelled = pushBatchRunnable.isCancelled();
      if (isExecutorCancelled) {
        break;
      }
      Thread.sleep(DELAY_TIME * (1L << retry));
    }
    // ScheduledFuture should be isCancelled now.
    assertThat(pushBatchRunnable.isCancelled()).isTrue();
  }

  @Test
  public void testEmptyBatchesAreNeverSent() throws Exception {
    UnaryCallable<LabeledIntList, List<Integer>> callable =
        new UnaryCallable<LabeledIntList, List<Integer>>() {
          @Override
          public ApiFuture<List<Integer>> futureCall(
              LabeledIntList request, ApiCallContext context) {
            throw new AssertionError("Should not call");
          }
        };
    underTest =
        new BatcherImpl<>(
            SQUARER_BATCHING_DESC_V2, callable, labeledIntList, batchingSettings, EXECUTOR);
    underTest.flush();
  }

  /** To confirm the partial failures in Batching does not mark whole batch failed */
  @Test
  public void testPartialFailureWithSplitResponse() throws Exception {
    SquarerBatchingDescriptorV2 descriptor =
        new SquarerBatchingDescriptorV2() {
          @Override
          public void splitResponse(
              List<Integer> batchResponse, List<BatchEntry<Integer, Integer>> batch) {
            for (int i = 0; i < batchResponse.size(); i++) {
              if (batchResponse.get(i) > 10_000) {
                batch.get(i).getResultFuture().setException(new ArithmeticException());
              } else {
                batch.get(i).getResultFuture().set(batchResponse.get(i));
              }
            }
          }
        };

    underTest =
        new BatcherImpl<>(
            descriptor, callLabeledIntSquarer, labeledIntList, batchingSettings, EXECUTOR);
    underTest.add(10);
    // This will cause partial failure
    underTest.add(200);
    underTest.flush();

    underTest.add(40);
    underTest.add(50);
    underTest.flush();

    // These will cause partial failure
    underTest.add(500);
    underTest.add(600);
    Exception actualError = null;
    try {
      underTest.close();
    } catch (Exception e) {
      actualError = e;
    }
    assertThat(actualError).isInstanceOf(BatchingException.class);
    assertThat(actualError)
        .hasMessageThat()
        .contains(
            "Batching finished with 2 partial failures. The 2 partial failures contained "
                + "3 entries that failed with: 3 ArithmeticException.");
  }

  @Test
  public void testPartialFailureInResultProcessing() throws Exception {
    final Queue<RuntimeException> queue = Queues.newArrayBlockingQueue(3);
    queue.add(new NullPointerException());
    queue.add(new RuntimeException());
    queue.add(new ArithmeticException());

    SquarerBatchingDescriptorV2 descriptor =
        new SquarerBatchingDescriptorV2() {

          @Override
          public void splitResponse(
              List<Integer> batchResponse, List<BatchEntry<Integer, Integer>> batch) {
            throw queue.poll();
          }
        };

    underTest =
        new BatcherImpl<>(
            descriptor, callLabeledIntSquarer, labeledIntList, batchingSettings, EXECUTOR);
    // This batch should fail with NullPointerException
    underTest.add(10);
    underTest.flush();

    // This batch should fail with RuntimeException
    underTest.add(20);
    underTest.add(30);
    underTest.flush();

    // This batch should fail with ArithmeticException
    underTest.add(40);
    underTest.add(50);
    underTest.add(60);

    Exception actualError = null;
    try {
      underTest.close();
    } catch (Exception e) {
      actualError = e;
    }
    assertThat(actualError).isInstanceOf(BatchingException.class);
    assertThat(actualError)
        .hasMessageThat()
        .contains("Batching finished with 3 batches failed to apply due to:");
    assertThat(actualError).hasMessageThat().contains("1 NullPointerException");
    assertThat(actualError).hasMessageThat().contains("1 RuntimeException");
    assertThat(actualError).hasMessageThat().contains("1 ArithmeticException");
    assertThat(actualError).hasMessageThat().contains(" and 0 partial failures.");
  }

  /**
   * Validates the presence of warning in case {@link BatcherImpl} is garbage collected without
   * being closed first.
   *
   * <p>Note:This test cannot run concurrently with other tests that use Batchers.
   */
  @Test
  public void testUnclosedBatchersAreLogged() throws Exception {
    final long DELAY_TIME = 30L;
    int actualRemaining = 0;
    for (int retry = 0; retry < 3; retry++) {
      System.gc();
      System.runFinalization();
      actualRemaining = BatcherReference.cleanQueue();
      if (actualRemaining == 0) {
        break;
      }
      Thread.sleep(DELAY_TIME * (1L << retry));
    }
    assertThat(actualRemaining).isAtMost(0);
    underTest = createDefaultBatcherImpl(batchingSettings, null);
    Batcher<Integer, Integer> extraBatcher = createDefaultBatcherImpl(batchingSettings, null);

    // Try to capture the log output but without causing terminal noise.  Adding the filter must
    // be done before clearing the ref or else it might be missed.
    final List<LogRecord> records = new ArrayList<>(1);
    Logger batcherLogger = Logger.getLogger(BatcherImpl.class.getName());
    Filter oldFilter = batcherLogger.getFilter();
    batcherLogger.setFilter(
        new Filter() {
          @Override
          public boolean isLoggable(LogRecord record) {
            synchronized (records) {
              records.add(record);
            }
            return false;
          }
        });

    try {
      // extraBatcher should not create any noise in the console as we called close() on it.
      extraBatcher.close();
      extraBatcher = null;

      underTest = null;
      // That *should* have been the last reference.  Try to reclaim it.
      boolean success = false;
      for (int retry = 0; retry < 3; retry++) {
        System.gc();
        System.runFinalization();
        int orphans = BatcherReference.cleanQueue();
        if (orphans == 1) {
          success = true;
          break;
        }
        // Validates that there are no other batcher instance present while GC cleanup.
        assertWithMessage("unexpected extra orphans").that(orphans).isEqualTo(0);
        Thread.sleep(DELAY_TIME * (1L << retry));
      }
      assertWithMessage("Batcher was not garbage collected").that(success).isTrue();

      LogRecord lr;
      synchronized (records) {
        assertThat(records.size()).isEqualTo(1);
        lr = records.get(0);
      }
      assertThat(lr.getMessage()).contains("not closed properly");
      assertThat(lr.getLevel()).isEqualTo(Level.SEVERE);
    } finally {
      batcherLogger.setFilter(oldFilter);
    }
  }

  /**
   * Validates the absence of warning in case {@link BatcherImpl} is garbage collected after being
   * closed.
   *
   * <p>Note:This test cannot run concurrently with other tests that use Batchers.
   */
  @Test
  public void testClosedBatchersAreNotLogged() throws Exception {
    // Clean out the existing instances
    final long DELAY_TIME = 30L;
    int actualRemaining = 0;
    for (int retry = 0; retry < 3; retry++) {
      System.gc();
      System.runFinalization();
      actualRemaining = BatcherReference.cleanQueue();
      if (actualRemaining == 0) {
        break;
      }
      Thread.sleep(DELAY_TIME * (1L << retry));
    }
    assertThat(actualRemaining).isAtMost(0);

    // Capture logs
    final List<LogRecord> records = new ArrayList<>(1);
    Logger batcherLogger = Logger.getLogger(BatcherImpl.class.getName());
    Filter oldFilter = batcherLogger.getFilter();
    batcherLogger.setFilter(
        new Filter() {
          @Override
          public boolean isLoggable(LogRecord record) {
            synchronized (records) {
              records.add(record);
            }
            return false;
          }
        });

    try {
      // Create a bunch of batchers that will garbage collected after being closed
      for (int i = 0; i < 1_000; i++) {
        BatcherImpl<Integer, Integer, LabeledIntList, List<Integer>> batcher =
            createDefaultBatcherImpl(batchingSettings, null);
        batcher.add(1);

        if (i % 2 == 0) {
          batcher.close();
        } else {
          batcher.closeAsync();
        }
      }
      // Run GC a few times to give the batchers a chance to be collected
      for (int retry = 0; retry < 100; retry++) {
        System.gc();
        System.runFinalization();
        BatcherReference.cleanQueue();
        Thread.sleep(10);
      }

      synchronized (records) {
        assertThat(records).isEmpty();
      }
    } finally {
      // reset logging
      batcherLogger.setFilter(oldFilter);
    }
  }

  @Test
  public void testCloseRace() throws ExecutionException, InterruptedException, TimeoutException {
    int iterations = 1_000_000;
    ExecutorService executor = Executors.newFixedThreadPool(100);

    try {
      List<Future<?>> closeFutures = new ArrayList<>();

      for (int i = 0; i < iterations; i++) {
        final SettableApiFuture<List<Integer>> result = SettableApiFuture.create();

        UnaryCallable<LabeledIntList, List<Integer>> callable =
            new UnaryCallable<LabeledIntList, List<Integer>>() {
              @Override
              public ApiFuture<List<Integer>> futureCall(
                  LabeledIntList request, ApiCallContext context) {
                return result;
              }
            };
        final Batcher<Integer, Integer> batcher =
            new BatcherImpl<>(
                SQUARER_BATCHING_DESC_V2, callable, labeledIntList, batchingSettings, EXECUTOR);

        batcher.add(1);

        executor.execute(
            new Runnable() {
              @Override
              public void run() {
                result.set(ImmutableList.of(1));
              }
            });
        Future<?> f =
            executor.submit(
                new Runnable() {
                  @Override
                  public void run() {
                    try {
                      batcher.close();
                    } catch (InterruptedException e) {
                      Thread.currentThread().interrupt();
                      throw new RuntimeException(e);
                    }
                  }
                });

        closeFutures.add(f);
      }

      // Make sure that none hang
      for (Future<?> f : closeFutures) {
        try {
          // Should never take this long, but padded just in case this runs on a limited machine
          f.get(1, TimeUnit.MINUTES);
        } catch (TimeoutException e) {
          assertWithMessage("BatcherImpl.close() is deadlocked").fail();
        }
      }
    } finally {
      executor.shutdownNow();
    }
  }

  @Test
  public void testConstructors() throws InterruptedException {
    try (BatcherImpl batcher1 = createDefaultBatcherImpl(batchingSettings, null)) {
      assertThat(batcher1.getFlowController()).isNotNull();
      assertThat(batcher1.getFlowController().getLimitExceededBehavior())
          .isEqualTo(batchingSettings.getFlowControlSettings().getLimitExceededBehavior());
      assertThat(batcher1.getFlowController().getMaxElementCountLimit())
          .isEqualTo(batchingSettings.getFlowControlSettings().getMaxOutstandingElementCount());
      assertThat(batcher1.getFlowController().getMaxRequestBytesLimit())
          .isEqualTo(batchingSettings.getFlowControlSettings().getMaxOutstandingRequestBytes());
    }

    FlowController flowController =
        new FlowController(
            FlowControlSettings.newBuilder()
                .setLimitExceededBehavior(LimitExceededBehavior.ThrowException)
                .setMaxOutstandingRequestBytes(6000L)
                .build());
    try (BatcherImpl batcher2 = createDefaultBatcherImpl(batchingSettings, flowController)) {
      assertThat(batcher2.getFlowController()).isSameInstanceAs(flowController);
    }
  }

  @Test
  public void testThrottlingBlocking() throws Exception {
    BatchingSettings settings =
        BatchingSettings.newBuilder()
            .setElementCountThreshold(1L)
            .setRequestByteThreshold(1L)
            .build();
    FlowController flowController =
        new FlowController(
            FlowControlSettings.newBuilder()
                .setLimitExceededBehavior(LimitExceededBehavior.Block)
                .setMaxOutstandingElementCount(1L)
                .build());
    ExecutorService executor = Executors.newFixedThreadPool(2);

    ApiCallContext callContext = Mockito.mock(ApiCallContext.class);
    ArgumentCaptor<ApiCallContext.Key<Long>> key =
        ArgumentCaptor.forClass(ApiCallContext.Key.class);
    ArgumentCaptor<Long> value = ArgumentCaptor.forClass(Long.class);
    when(callContext.withOption(key.capture(), value.capture())).thenReturn(callContext);
    long throttledTime = 50;

    try (final Batcher<Integer, Integer> batcher =
        new BatcherImpl<>(
            SQUARER_BATCHING_DESC_V2,
            callLabeledIntSquarer,
            labeledIntList,
            settings,
            EXECUTOR,
            flowController,
            callContext)) {
      flowController.reserve(1, 1);
      Future future =
          executor.submit(
              new Runnable() {
                @Override
                public void run() {
                  batcher.add(1);
                }
              });
      // Add a little delay ensuring that the next step starts after batcher.add(1)
      Thread.sleep(10);
      executor.submit(
          () -> {
            try {
              Thread.sleep(throttledTime);
              flowController.release(1, 1);
            } catch (InterruptedException e) {
            }
          });

      try {
        future.get(10, TimeUnit.MILLISECONDS);
        assertWithMessage("adding elements to batcher should be blocked by FlowControlled").fail();
      } catch (TimeoutException e) {
        // expected
      }

      try {
        future.get(3, TimeUnit.SECONDS);
      } catch (TimeoutException e) {
        assertWithMessage("adding elements to batcher should not be blocked").fail();
      }

      // Mockito recommends using verify() as the ONLY way to interact with Argument
      // captors - otherwise it may incur in unexpected behaviour
      Mockito.verify(callContext, Mockito.timeout(100)).withOption(key.capture(), value.capture());

      // Verify that throttled time is recorded in ApiCallContext
      assertThat(key.getValue()).isSameInstanceAs(Batcher.THROTTLED_TIME_KEY);
      assertThat(value.getValue()).isAtLeast(throttledTime);
    } finally {
      executor.shutdownNow();
    }
  }

  @Test
  public void testThrottlingNonBlocking() throws Exception {
    BatchingSettings settings =
        BatchingSettings.newBuilder()
            .setElementCountThreshold(1L)
            .setRequestByteThreshold(1L)
            .build();
    FlowController flowController =
        new FlowController(
            FlowControlSettings.newBuilder()
                .setLimitExceededBehavior(LimitExceededBehavior.ThrowException)
                .setMaxOutstandingElementCount(1L)
                .build());
    try (final Batcher<Integer, Integer> batcher =
        createDefaultBatcherImpl(settings, flowController)) {
      flowController.reserve(1, 1);
      try {
        batcher.add(1);
        assertWithMessage("Should throw exception because it exceeded FlowController limit").fail();
      } catch (FlowControlRuntimeException e) {
        assertThat(e.getMessage()).contains("The maximum number of batch elements");
      }
      flowController.release(1, 1);
      batcher.add(1);
    }
  }

  /**
   * If the batcher's unary callable throws an exception when obtaining a response, then the
   * response .get() should throw the exception
   */
  @Test
  public void testAddDoesNotHangIfExceptionThrowStartingACall() {
    BatchingDescriptor<Object, Object, Object, Object> batchingDescriptor =
        new BatchingDescriptor<Object, Object, Object, Object>() {
          @Override
          public BatchingRequestBuilder<Object, Object> newRequestBuilder(Object o) {
            return new BatchingRequestBuilder<Object, Object>() {
              @Override
              public void add(Object o) {}

              @Override
              public Object build() {
                return new Object();
              }
            };
          }

          @Override
          public void splitResponse(Object o, List<BatchEntry<Object, Object>> list) {
            for (BatchEntry<Object, Object> e : list) {
              e.getResultFuture().set(new Object());
            }
          }

          @Override
          public void splitException(Throwable throwable, List<BatchEntry<Object, Object>> list) {
            for (BatchEntry<Object, Object> e : list) {
              e.getResultFuture().setException(new RuntimeException("fake"));
            }
          }

          @Override
          public long countBytes(Object o) {
            return 1;
          }
        };

    UnaryCallable<Object, Object> unaryCallable =
        new UnaryCallable<Object, Object>() {
          @Override
          public ApiFuture<Object> futureCall(Object o, ApiCallContext apiCallContext) {
            throw new RuntimeException("this should bubble up");
          }
        };
    Object prototype = new Object();
    BatchingSettings batchingSettings =
        BatchingSettings.newBuilder()
            .setDelayThreshold(java.time.Duration.ofSeconds(1))
            .setElementCountThreshold(100L)
            .setRequestByteThreshold(100L)
            .setFlowControlSettings(FlowControlSettings.getDefaultInstance())
            .build();
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    FlowController flowController = new FlowController(batchingSettings.getFlowControlSettings());
    ApiCallContext callContext = FakeCallContext.createDefault();

    BatcherImpl<Object, Object, Object, Object> batcher =
        new BatcherImpl<>(
            batchingDescriptor,
            unaryCallable,
            prototype,
            batchingSettings,
            executor,
            flowController,
            callContext);

    ApiFuture<Object> f = batcher.add(new Object());
    Assert.assertThrows(ExecutionException.class, f::get);
    // bubbles up
    Assert.assertThrows(RuntimeException.class, batcher::close);
  }

  @Test
  public void testDefaultShouldFlush() {
    BatchResource resource =
        DefaultBatchResource.builder().setElementCount(2).setByteCount(2).build();

    assertThat(resource.shouldFlush(2, 2)).isFalse();
    assertThat(resource.shouldFlush(1, 1)).isTrue();
  }

  @Test
  public void testDefaultBatchResourceAdd() {
    BatchResource resource =
        DefaultBatchResource.builder().setElementCount(1).setByteCount(1).build();

    BatchResource newResource =
        resource.add(DefaultBatchResource.builder().setElementCount(1).setByteCount(1).build());

    // Make sure add doesn't modify the old object
    assertThat(resource.getElementCount()).isEqualTo(1);
    assertThat(resource.getByteCount()).isEqualTo(1);
    assertThat(newResource.getElementCount()).isEqualTo(2);
    assertThat(newResource.getByteCount()).isEqualTo(2);
  }

  private void testElementTriggers(BatchingSettings settings) throws Exception {
    underTest = createDefaultBatcherImpl(settings, null);
    Future<Integer> result = underTest.add(4);
    assertThat(result.isDone()).isFalse();
    Future<Integer> anotherResult = underTest.add(5);
    // After this element is added, the batch triggers sendOutstanding().
    underTest.add(6);
    // Both the elements should be resolved now.
    assertThat(result.isDone()).isTrue();
    assertThat(result.get()).isEqualTo(16);
    assertThat(anotherResult.isDone()).isTrue();
  }

  private BatcherImpl<Integer, Integer, LabeledIntList, List<Integer>> createDefaultBatcherImpl(
      BatchingSettings settings, FlowController flowController) {
    return new BatcherImpl<>(
        SQUARER_BATCHING_DESC_V2,
        callLabeledIntSquarer,
        labeledIntList,
        settings,
        EXECUTOR,
        flowController);
  }
}
