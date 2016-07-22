package lv.vitalik.concurrency.readers_writers;

import lv.vitalik.concurrency.readers_writers.shared_resource.Resource;
import lv.vitalik.concurrency.readers_writers.shared_resource.SharedResourceManualSemaphores;
import lv.vitalik.concurrency.readers_writers.shared_resource.SharedResourceReadWriteLock;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public class Main {
    public static final int SLEEP = 500;

    public static void main(String[] args) {
//        Resource resource = new SharedResourceReadWriteLock();
        Resource resource = new SharedResourceManualSemaphores();

        Reader r1 = new Reader("R1", resource);
        r1.start();
        Utils.sleep(SLEEP);

        Reader r2 = new Reader("R2", resource);
        r2.start();
        Utils.sleep(SLEEP);

        Writer w1 = new Writer("W1", "Jumped over a lazy dog. ", resource);
        w1.start();
        Utils.sleep(SLEEP);

        Reader r3 = new Reader("R3", resource);
        r3.start();
        Utils.sleep(SLEEP);
    }
}
