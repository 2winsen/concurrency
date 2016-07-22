package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalijs on 18-Apr-16.
 */
public class ThreadFactory {

    public enum Type {
        PRODUCER,
        CONSUMER;
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public static Thread make(Type type, Queue<String> queue, String name) throws Exception {
        Thread thread = null;
        switch (type) {
            case PRODUCER:
                thread = new Producer(queue, name);
                break;
            case CONSUMER:
                thread = new Consumer(queue, name);
                break;
        }
        return thread;
    }

    public static List<Thread> makeMany(Type type, Queue<String> queue, int count) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int threadIndex = i + 1;
            try {
                Thread thread = make(type, queue, type.toString() + threadIndex);
                threads.add(thread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return threads;
    }

}
