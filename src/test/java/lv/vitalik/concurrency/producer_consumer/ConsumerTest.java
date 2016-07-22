package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    static final int WAITING_INTERVAL = 10;
    static final int CONSUMPTION_DELAY = 10;
    static final String CONSUMER_NAME = "TEST_CONSUMER";
    Consumer consumer;

    @Mock
    Queue<String> queue;

    @Before
    public void setUp() throws Exception {
        consumer = new Consumer(queue, CONSUMER_NAME);
        consumer.setConsumptionDelay(CONSUMPTION_DELAY);
        consumer.setWaitingInterval(WAITING_INTERVAL);
        doReturn(null).when(queue).dequeue();
    }

    @Test(timeout = 1000)
    public void testRun() throws Exception {
        doReturn(false).when(queue).isEmpty();
        consumer.start();
        Thread.sleep(200);
        doReturn(true).when(queue).isEmpty();
        verify(queue, atLeastOnce()).dequeue();
    }

    @Test(timeout = 1000)
    public void testRun_queueEmpty() throws Exception {
        doReturn(true).when(queue).isEmpty();
        consumer.start();
        Thread.sleep(200);
        verify(queue, never()).dequeue();
    }
}