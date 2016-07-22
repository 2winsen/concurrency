package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProducerTest {

    static final int PRODUCTIONS_COUNT = 10;
    static final int PRODUCTION_DELAY = 10;
    static final String PRODUCER_NAME = "TEST_PRODUCER";
    Producer producer;

    @Mock
    Queue<String> queue;

    @Before
    public void setUp() throws Exception {
        producer = new Producer(queue, PRODUCER_NAME);
        producer.setProductionsCount(PRODUCTIONS_COUNT);
        producer.setProductionDelay(PRODUCTION_DELAY);
        doNothing().when(queue).enqueue(anyString());
    }

    @Test(timeout = 1000)
    public void testRun() throws Exception {
        producer.start();
        Thread.sleep(200);
        verify(queue, times(PRODUCTIONS_COUNT)).enqueue(matches(".*_PRODUCED_BY_" + PRODUCER_NAME));
    }
}