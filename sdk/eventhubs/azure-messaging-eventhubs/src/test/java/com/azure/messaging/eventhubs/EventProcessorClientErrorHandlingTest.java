// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.eventhubs;

import com.azure.core.util.metrics.Meter;
import com.azure.core.util.tracing.Tracer;
import com.azure.messaging.eventhubs.implementation.PartitionProcessor;
import com.azure.messaging.eventhubs.models.Checkpoint;
import com.azure.messaging.eventhubs.models.CloseContext;
import com.azure.messaging.eventhubs.models.ErrorContext;
import com.azure.messaging.eventhubs.models.EventContext;
import com.azure.messaging.eventhubs.models.EventPosition;
import com.azure.messaging.eventhubs.models.InitializationContext;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.azure.messaging.eventhubs.models.PartitionEvent;
import com.azure.messaging.eventhubs.models.PartitionOwnership;
import com.azure.messaging.eventhubs.models.ReceiveOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.azure.messaging.eventhubs.EventHubClientBuilder.DEFAULT_PREFETCH_COUNT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link EventProcessorClient} error handling.
 */
public class EventProcessorClientErrorHandlingTest {
    @Mock
    private EventHubClientBuilder eventHubClientBuilder;

    @Mock
    private EventHubAsyncClient eventHubAsyncClient;

    @Mock
    private EventHubConsumerAsyncClient eventHubConsumer;

    @Mock
    private EventData eventData1;

    @Mock
    private Tracer tracer;

    @Mock
    private Meter meter;

    private EventProcessorClientOptions processorOptions = new EventProcessorClientOptions();
    private CountDownLatch countDownLatch;
    private AutoCloseable mocksCloseable;

    @BeforeEach
    public void setup() {
        mocksCloseable = MockitoAnnotations.openMocks(this);
        when(eventHubClientBuilder.buildAsyncClient()).thenReturn(eventHubAsyncClient);
        when(eventHubAsyncClient.getPartitionIds()).thenReturn(Flux.just("1", "2", "3"));
        when(eventHubAsyncClient.getFullyQualifiedNamespace()).thenReturn("test-ns");
        when(eventHubAsyncClient.getEventHubName()).thenReturn("test-eh");
        when(eventHubAsyncClient.getIdentifier()).thenReturn("my-client-identifier");
    }

    @AfterEach
    public void afterEach() throws Exception {
        if (mocksCloseable != null) {
            mocksCloseable.close();
        }
    }

    @ParameterizedTest(name = "{displayName} with [{arguments}]")
    @MethodSource("checkpointStoreSupplier")
    public void testCheckpointStoreErrors(CheckpointStore checkpointStore) throws InterruptedException {
        countDownLatch = new CountDownLatch(1);

        processorOptions.setConsumerGroup("cg")
            .setTrackLastEnqueuedEventProperties(false)
            .setInitialEventPositionProvider(null)
            .setMaxBatchSize(1)
            .setMaxWaitTime(null)
            .setBatchReceiveMode(false)
            .setLoadBalancerUpdateInterval(Duration.ofSeconds(10))
            .setPartitionOwnershipExpirationInterval(Duration.ofMinutes(1))
            .setLoadBalancingStrategy(LoadBalancingStrategy.BALANCED);

        EventProcessorClient client = new EventProcessorClient(eventHubClientBuilder,
            () -> new TestPartitionProcessor(), checkpointStore, errorContext -> {
                countDownLatch.countDown();
                Assertions.assertEquals("NONE", errorContext.getPartitionContext().getPartitionId());
                Assertions.assertEquals("cg", errorContext.getPartitionContext().getConsumerGroup());
                Assertions.assertTrue(errorContext.getThrowable() instanceof IllegalStateException);
            }, tracer, null, processorOptions);
        client.start();
        boolean completed = countDownLatch.await(10, TimeUnit.SECONDS);
        try {
            client.stop();
        } catch (IllegalStateException ex) {
            // do nothing, expected as the checkpointstores are expected to throw errors
        }
        Assertions.assertTrue(completed);
    }

    @Test
    public void testProcessEventHandlerError() throws InterruptedException {
        countDownLatch = new CountDownLatch(1);
        when(eventHubClientBuilder.getPrefetchCount()).thenReturn(DEFAULT_PREFETCH_COUNT);
        when(eventHubAsyncClient.createConsumer("cg", DEFAULT_PREFETCH_COUNT, true)).thenReturn(eventHubConsumer);
        when(eventHubConsumer.receiveFromPartition(anyString(), any(EventPosition.class), any(ReceiveOptions.class)))
            .thenReturn(Flux.just(getEvent(eventData1)));

        processorOptions.setConsumerGroup("cg")
            .setTrackLastEnqueuedEventProperties(false)
            .setInitialEventPositionProvider(null)
            .setMaxBatchSize(1)
            .setMaxWaitTime(null)
            .setBatchReceiveMode(false)
            .setLoadBalancerUpdateInterval(Duration.ofSeconds(10))
            .setPartitionOwnershipExpirationInterval(Duration.ofMinutes(1))
            .setLoadBalancingStrategy(LoadBalancingStrategy.BALANCED);

        EventProcessorClient client = new EventProcessorClient(eventHubClientBuilder,
            () -> new BadProcessEventHandler(countDownLatch), new SampleCheckpointStore(), errorContext -> {
            }, tracer, null, processorOptions);
        client.start();
        boolean completed = countDownLatch.await(3, TimeUnit.SECONDS);
        client.stop();
        Assertions.assertTrue(completed);
    }

    @Test
    public void testInitHandlerError() throws InterruptedException {
        countDownLatch = new CountDownLatch(1);
        when(eventHubAsyncClient.createConsumer("cg", DEFAULT_PREFETCH_COUNT, true)).thenReturn(eventHubConsumer);
        when(eventHubConsumer.receiveFromPartition(anyString(), any(EventPosition.class), any(ReceiveOptions.class)))
            .thenReturn(Flux.just(getEvent(eventData1)));

        processorOptions.setConsumerGroup("cg")
            .setTrackLastEnqueuedEventProperties(false)
            .setInitialEventPositionProvider(null)
            .setMaxBatchSize(1)
            .setMaxWaitTime(null)
            .setBatchReceiveMode(false)
            .setLoadBalancerUpdateInterval(Duration.ofSeconds(10))
            .setPartitionOwnershipExpirationInterval(Duration.ofMinutes(1))
            .setLoadBalancingStrategy(LoadBalancingStrategy.BALANCED);

        EventProcessorClient client = new EventProcessorClient(eventHubClientBuilder,
            () -> new BadInitHandler(countDownLatch), new SampleCheckpointStore(), errorContext -> {
            }, tracer, null, processorOptions);
        client.start();
        boolean completed = countDownLatch.await(3, TimeUnit.SECONDS);
        client.stop();
        Assertions.assertTrue(completed);
    }

    @Test
    public void testCloseHandlerError() throws InterruptedException {
        countDownLatch = new CountDownLatch(1);
        when(eventHubClientBuilder.getPrefetchCount()).thenReturn(DEFAULT_PREFETCH_COUNT);
        when(eventHubAsyncClient.createConsumer("cg", DEFAULT_PREFETCH_COUNT, true)).thenReturn(eventHubConsumer);
        when(eventHubConsumer.receiveFromPartition(anyString(), any(EventPosition.class), any(ReceiveOptions.class)))
            .thenReturn(Flux.just(getEvent(eventData1)));

        processorOptions.setConsumerGroup("cg")
            .setTrackLastEnqueuedEventProperties(false)
            .setInitialEventPositionProvider(null)
            .setMaxBatchSize(1)
            .setMaxWaitTime(null)
            .setBatchReceiveMode(false)
            .setLoadBalancerUpdateInterval(Duration.ofSeconds(10))
            .setPartitionOwnershipExpirationInterval(Duration.ofMinutes(1))
            .setLoadBalancingStrategy(LoadBalancingStrategy.BALANCED);

        EventProcessorClient client = new EventProcessorClient(eventHubClientBuilder,
            () -> new BadCloseHandler(countDownLatch), new SampleCheckpointStore(), errorContext -> {
            }, tracer, null, processorOptions);

        client.start();
        boolean completed = countDownLatch.await(3, TimeUnit.SECONDS);
        client.stop();
        Assertions.assertTrue(completed);
    }

    private static Stream<Arguments> checkpointStoreSupplier() {
        return Stream.of(Arguments.of(new ListOwnershipErrorStore()), Arguments.of(new ClaimOwnershipErrorStore()),
            Arguments.of(new ListCheckpointErrorStore()));
    }

    private PartitionEvent getEvent(EventData event) {
        PartitionContext context = new PartitionContext("ns", "foo", "bar", "baz");
        return new PartitionEvent(context, event, null);
    }

    private static class ListOwnershipErrorStore implements CheckpointStore {

        @Override
        public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return Flux.error(new IllegalStateException("List ownership error"));
        }

        @Override
        public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
            return null;
        }

        @Override
        public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return null;
        }

        @Override
        public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
            return null;
        }
    }

    private static class ClaimOwnershipErrorStore implements CheckpointStore {

        @Override
        public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return Flux.empty();
        }

        @Override
        public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
            return Flux.error(new IllegalStateException("Claim Ownership error"));
        }

        @Override
        public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return Flux.empty();
        }

        @Override
        public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
            return null;
        }
    }

    private static class ListCheckpointErrorStore implements CheckpointStore {

        @Override
        public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return Flux.empty();
        }

        @Override
        public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
            return Flux.fromIterable(requestedPartitionOwnerships);
        }

        @Override
        public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName,
            String consumerGroup) {
            return Flux.error(new IllegalStateException("List checkpoint error"));
        }

        @Override
        public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
            return null;
        }
    }

    private static final class TestPartitionProcessor extends PartitionProcessor {

        @Override
        public void processEvent(EventContext eventContext) {
            eventContext.updateCheckpoint();
        }

        @Override
        public void processError(ErrorContext errorContext) {
            // do nothing
            return;
        }
    }

    private static final class BadProcessEventHandler extends PartitionProcessor {

        CountDownLatch countDownLatch;

        BadProcessEventHandler(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void processEvent(EventContext eventContext) {
            countDownLatch.countDown();
            throw new IllegalStateException("Process event error");
        }

        @Override
        public void processError(ErrorContext errorContext) {
            Assertions.fail("Process error handler should not be called when process event throws exception");
            return;
        }
    }

    private static final class BadInitHandler extends PartitionProcessor {

        CountDownLatch countDownLatch;

        BadInitHandler(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void initialize(InitializationContext initContext) {
            countDownLatch.countDown();
            throw new IllegalStateException("Init error");
        }

        @Override
        public void processEvent(EventContext eventContext) {
            Assertions.fail("Process event handler should not be called when there's an error during initialization");
        }

        @Override
        public void processError(ErrorContext errorContext) {
            Assertions.fail("Process error handler should not be called when process event throws exception");
            return;
        }
    }

    private static final class BadCloseHandler extends PartitionProcessor {

        CountDownLatch countDownLatch;

        BadCloseHandler(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void close(CloseContext closeContext) {
            countDownLatch.countDown();
            throw new IllegalStateException("Close error");
        }

        @Override
        public void processEvent(EventContext eventContext) {
            // do nothing
        }

        @Override
        public void processError(ErrorContext errorContext) {
            // do nothing
        }
    }
}
