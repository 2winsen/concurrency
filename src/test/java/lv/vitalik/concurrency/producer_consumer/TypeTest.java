package lv.vitalik.concurrency.producer_consumer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeTest {

    @Test
    public void testToString_PRODUCER() throws Exception {
        assertEquals("Producer", ThreadFactory.Type.PRODUCER.toString());
    }

    @Test
    public void testToString_CONSUMER() throws Exception {
        assertEquals("Consumer", ThreadFactory.Type.CONSUMER.toString());
    }
}