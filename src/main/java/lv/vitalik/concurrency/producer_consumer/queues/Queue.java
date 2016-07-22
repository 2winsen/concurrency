package lv.vitalik.concurrency.producer_consumer.queues;

/**
 * Created by Vitalijs on 18-Apr-16.
 */
public interface Queue<T> {

    public void enqueue(T queueElement) throws InterruptedException;

    public T dequeue() throws InterruptedException;

    public boolean isEmpty();

    public int size();

}
