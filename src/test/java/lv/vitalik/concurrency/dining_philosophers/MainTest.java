package lv.vitalik.concurrency.dining_philosophers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testSetupForks() throws Exception {
        int count = 3;
        List<Fork> forks = Main.setupForks(count);
        assertEquals(forks.get(0).getIndex(), 0);
        assertEquals(forks.get(1).getIndex(), 1);
        assertEquals(forks.get(2).getIndex(), 2);
    }

    @Test
    public void testSetupPhilosophers_firstPhilosopher() throws Exception {
        int count = 3;
        Fork leftFork;
        Fork rightFork;
        int philosopherIndex;
        List<Philosopher> philosophers = Main.setupPhilosophers(count);

        philosopherIndex = 0;
        assertEquals(philosophers.get(philosopherIndex).getIndex(), philosopherIndex);
        leftFork = philosophers.get(philosopherIndex).getLeftFork();
        rightFork = philosophers.get(philosopherIndex).getRightFork();
        assertEquals(leftFork.getIndex(), philosopherIndex);
        assertEquals(rightFork.getIndex(), 2);
    }

    @Test
    public void testSetupPhilosophers_lastPhilosopher() throws Exception {
        int count = 3;
        Fork leftFork;
        Fork rightFork;
        int philosopherIndex;
        List<Philosopher> philosophers = Main.setupPhilosophers(count);

        philosopherIndex = 2;
        assertEquals(philosophers.get(philosopherIndex).getIndex(), philosopherIndex);
        leftFork = philosophers.get(philosopherIndex).getLeftFork();
        rightFork = philosophers.get(philosopherIndex).getRightFork();
        assertEquals(leftFork.getIndex(), philosopherIndex);
        assertEquals(rightFork.getIndex(), 1);
    }
}