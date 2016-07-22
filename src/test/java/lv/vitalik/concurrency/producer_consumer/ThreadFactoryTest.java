package lv.vitalik.concurrency.producer_consumer;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ThreadFactoryTest {

    @Test
    public void testMake_PRODUCER() throws Exception {
        Thread thread = ThreadFactory.make(ThreadFactory.Type.PRODUCER, null, "TEST_PRODUCER");
        assertThat(thread, instanceOf(Producer.class));
    }

    @Test
    public void testMake_CONSUMER() throws Exception {
        Thread thread = ThreadFactory.make(ThreadFactory.Type.CONSUMER, null, "TEST_CONSUMER");
        assertThat(thread, instanceOf(Consumer.class));
    }

    @Test
    public void testMakeMany_PRODUCER() throws Exception {
        final int COUNT = 5;
        List<Thread> threads = ThreadFactory.makeMany(ThreadFactory.Type.PRODUCER, null, COUNT);
        assertEquals(COUNT, threads.size());
        threads.forEach(t -> assertThat(t, instanceOf(Producer.class)));
    }
    @Test
    public void testMakeMany_CONSUMER() throws Exception {
        final int COUNT = 5;
        List<Thread> threads = ThreadFactory.makeMany(ThreadFactory.Type.CONSUMER, null, COUNT);
        assertEquals(COUNT, threads.size());
        threads.forEach(t -> assertThat(t, instanceOf(Consumer.class)));
    }

    @Test
    public void testMake_MANY() throws Exception {
        final int COUNT = 100;
        List<Thread> threads = ThreadFactory.makeMany(ThreadFactory.Type.PRODUCER, null, COUNT);
        assertEquals(COUNT, threads.size());
        threads.forEach(t -> assertThat(t, instanceOf(Producer.class)));
    }

    @Test
    public void testMake_ZERO() throws Exception {
        final int COUNT = 0;
        List<Thread> threads = ThreadFactory.makeMany(ThreadFactory.Type.PRODUCER, null, COUNT);
        assertEquals(COUNT, threads.size());
    }
}