package lv.vitalik.concurrency.readers_writers.shared_resource;

import lv.vitalik.concurrency.readers_writers.Utils;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public class SharedResourceReadWriteLock implements Resource {
    public static final boolean IS_FAIR = true;
    private String text = "A quick brown fox. ";
    private ReadWriteLock lock = new ReentrantReadWriteLock(IS_FAIR);

    public void read(String name) {
        lock.readLock().lock();
        for (int i = 0; i < text.length(); i++) {
            System.out.println(name + " <<< " + text.charAt(i));
            Utils.randomSleep();
        }
        lock.readLock().unlock();
    }

    public void write(String name, String appendedText) {
        lock.writeLock().lock();
        this.text += appendedText;
        System.out.println(name + " >>> " + text);
        lock.writeLock().unlock();
    }
}
