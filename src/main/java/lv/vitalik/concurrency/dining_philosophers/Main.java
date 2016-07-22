package lv.vitalik.concurrency.dining_philosophers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalijs on 21-Jul-16.
 */
public class Main {
    public static void main(String[] args) {
        final int COUNT = 5;
        List<Philosopher> philosophers = setupPhilosophers(COUNT);
        philosophers.forEach(Philosopher::start);
    }

    public static List<Fork> setupForks(int count) {
        List<Fork> forks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            forks.add(new Fork(i));
        }
        return forks;
    }

    public static List<Philosopher> setupPhilosophers(int count) {
        List<Fork> forks = setupForks(count);
        List<Philosopher> philosophers = new ArrayList<>();
        for (int philosopherIndex = 0; philosopherIndex < count; philosopherIndex++) {
            boolean isFirstPhilosopher = philosopherIndex == 0;
            Fork leftFork = forks.get(philosopherIndex);
            Fork rightFork;
            if (isFirstPhilosopher) {
                rightFork = forks.get(forks.size() - 1);
            } else {
                rightFork = forks.get(philosopherIndex - 1);
            }
            philosophers.add(new Philosopher(philosopherIndex, leftFork, rightFork));
        }
        return philosophers;
    }

}
