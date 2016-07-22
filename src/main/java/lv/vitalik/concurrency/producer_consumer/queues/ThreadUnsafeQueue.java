package lv.vitalik.concurrency.producer_consumer.queues;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Vitalijs on 18-Apr-16.
 */
public class ThreadUnsafeQueue<T> implements Queue<T> {

    private static final int MAX_WAIT_INTERVAL = 5000;
    private LinkedList<T> queue;
    private final int maxCapacity;

    public ThreadUnsafeQueue(int capacity) {
        this.queue = new LinkedList<>(new ArrayList<>(capacity));
        this.maxCapacity = capacity;
    }

    @Override
    public void enqueue(T queueElement) throws InterruptedException {
        enqueueSynchronized(queueElement);
    }

    synchronized private void enqueueSynchronized(T queueElement) throws InterruptedException {
        if (!isMaxCapacity()) {
            queue.add(queueElement);
            notify();
        } else {
            wait(MAX_WAIT_INTERVAL);
        }
    }

    private boolean isMaxCapacity() {
        return queue.size() >= maxCapacity;
    }

    @Override
    public T dequeue() throws InterruptedException {
        return dequeueSynchronized();
    }

    synchronized private T dequeueSynchronized() throws InterruptedException {
        T queueElement = null;
        if (!isEmpty()) {
            queueElement = queue.remove();
            notify();
        } else {
            wait(MAX_WAIT_INTERVAL);
        }
        return queueElement;
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
