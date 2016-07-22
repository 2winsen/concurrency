package lv.vitalik.concurrency.producer_consumer.queues;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadUnsafeQueueTest {

    static final int CAPACITY = 3;
    Queue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new ThreadUnsafeQueue<>(CAPACITY);
    }

    @Test
    public void testEnqueue() throws Exception {
        assertEquals(0, queue.size());
        queue.enqueue("TEST");
        assertEquals(1, queue.size());
    }

    @Test(timeout = 1000)
    public void testEnqueue_moreThanLimit() throws Exception {
        assertEquals(0, queue.size());
        Thread worker = new Thread(() -> {
            try {
                enqueueElementsMoreThanLimit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        worker.start();
        Thread.sleep(200);
        assertEquals(3, queue.size());
    }

    public void enqueueElementsMoreThanLimit() throws Exception {
        for (int i = 0; i < CAPACITY; i++) {
            queue.enqueue("TEST");
        }
    }

    @Test
    public void testDequeue() throws Exception {
        queue.enqueue("TEST");
        String target = queue.dequeue();
        assertEquals("TEST", target);
        assertEquals(0, queue.size());
    }

    @Test(timeout = 1000)
    public void testDequeue_zeroSize() throws Exception {
        assertEquals(0, queue.size());
        Thread worker = new Thread(() -> {
            try {
                queue.dequeue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        worker.start();
        Thread.sleep(200);
        assertEquals(0, queue.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(queue.isEmpty());
        queue.enqueue("Test");
        assertTrue(!queue.isEmpty());
    }

}