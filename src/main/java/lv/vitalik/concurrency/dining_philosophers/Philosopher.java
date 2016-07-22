package lv.vitalik.concurrency.dining_philosophers;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Vitalijs on 21-Jul-16.
 */
public class Philosopher extends Thread {
    private static final int LOOPS = 1;
    private int index;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int index, Fork leftFork, Fork rightFork) {
        this.index = index;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        for (int i = 0; i < LOOPS; i++) {
            eat();
            think();
        }
    }

    public void eat() {
        acquire();
        System.out.println("Philosopher " + this.index + " is EATING " + LocalDateTime.now());
        randomWait();
        System.out.println("Philosopher " + this.index + " is FINISHED EATING " + LocalDateTime.now());
        release();
    }

    private void acquire() {
        try {
            leftFork.acquire();
            rightFork.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void release() {
        leftFork.release();
        rightFork.release();
    }

    public static void randomWait() {
        int sleepTime = ThreadLocalRandom.current().nextInt(0, 3) * 1000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void think() {
        System.out.println("Philosopher " + this.index + " is THINKING " + LocalDateTime.now());
        randomWait();
    }

    public int getIndex() {
        return index;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }
}
