package lv.vitalik.concurrency.readers_writers.shared_resource;

import lv.vitalik.concurrency.readers_writers.Utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public class SharedResourceManualSemaphores implements Resource {
    private String text = "A quick brown fox. ";
    private AtomicInteger readersCount = new AtomicInteger(0);
    private int writersCount = 0;
    private Semaphore semaphore = new Semaphore(1);
    private Semaphore writeSemaphore = new Semaphore(1);

    public void read(String name) {
        initRead();
        for (int i = 0; i < text.length(); i++) {
            System.out.println(name + " <<< " + text.charAt(i));
            Utils.randomSleep();
        }
        endRead();
    }

    private void initRead() {
        try {
            writeSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (readersCount.incrementAndGet() == 1) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeSemaphore.release();
    }

    private void endRead() {
        if (readersCount.decrementAndGet() == 0) {
            semaphore.release();
        }
    }

    public synchronized void write(String name, String appendedText) {
        initWrite();
        this.text += appendedText;
        System.out.println(name + " >>> " + text);
        endWrite();
    }


    private void initWrite() {
        writersCount++;
        if (writersCount == 1) {
            try {
                writeSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endWrite() {
        semaphore.release();
        writersCount--;
        if (writersCount == 0) {
            writeSemaphore.release();
        }
    }
}
