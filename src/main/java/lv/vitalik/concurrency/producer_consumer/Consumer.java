package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;

/**
 * Created by Vitalijs on 15-Apr-16.
 */
public class Consumer extends Thread {

    private int consumptionDelay = 1000;
    private int waitingInterval = 100;
    private Queue<String> queue;
    private String name;

    public Consumer(Queue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            waitForWork();
            while (!queue.isEmpty()) {
                consume();
                Thread.sleep(consumptionDelay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForWork() throws InterruptedException{
        while (queue.isEmpty()) {
            Thread.sleep(waitingInterval);
        }
    }

    private void consume() throws InterruptedException {
        String headElement = queue.dequeue();
        System.out.println(headElement + "---" + "CONSUMED_BY_" + name);
    }

    public void setConsumptionDelay(int consumptionDelay) {
        this.consumptionDelay = consumptionDelay;
    }

    public void setWaitingInterval(int waitingInterval) {
        this.waitingInterval = waitingInterval;
    }
}
