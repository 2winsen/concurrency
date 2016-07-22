package lv.vitalik.concurrency.readers_writers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Vitalijs on 07-Jul-16.
 */
public class Utils {

    public static void randomSleep() {
        int sleepTime = ThreadLocalRandom.current().nextInt(0, 5) * 100;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
