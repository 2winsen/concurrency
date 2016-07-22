package lv.vitalik.concurrency.producer_consumer.queues;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Vitalijs on 15-Apr-16.
 */
public class BlockingQueue<T> implements Queue<T> {

    private java.util.concurrent.BlockingQueue<T> queue;

    public BlockingQueue(int capacity) {
        this.queue = new ArrayBlockingQueue<T>(capacity);
    }

    @Override
    public void enqueue(T queueElement) throws InterruptedException {
        queue.put(queueElement);
    }

    @Override
    public T dequeue() throws InterruptedException {
        return queue.take();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

}
