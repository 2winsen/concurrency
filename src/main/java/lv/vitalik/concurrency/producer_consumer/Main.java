package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;
import lv.vitalik.concurrency.producer_consumer.queues.ThreadUnsafeQueue;

/**
 * Created by Vitalijs on 15-Apr-16.
 */
public class Main {
    public static final int QUEUE_CAPACITY = 5;

    public static void main(String[] args) {
//        Queue<String> queue = new BlockingQueue<String>(QUEUE_CAPACITY);
        Queue<String> queue = new ThreadUnsafeQueue<String>(QUEUE_CAPACITY);

        ThreadFactory.makeMany(ThreadFactory.Type.PRODUCER, queue, 3).forEach(Thread::start);
        ThreadFactory.makeMany(ThreadFactory.Type.CONSUMER, queue, 3).forEach(Thread::start);
    }

}
