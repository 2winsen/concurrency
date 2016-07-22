package lv.vitalik.concurrency.producer_consumer;

import lv.vitalik.concurrency.producer_consumer.queues.Queue;

/**
 * Created by Vitalijs on 15-Apr-16.
 */
public class Producer extends Thread {

    private int productionDelay = 100;
    private int productionsCount = 10;
    private Queue<String> queue;
    private String name;

    public Producer(Queue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < productionsCount; i++) {
                produce(System.currentTimeMillis());
                Thread.sleep(productionDelay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void produce(long timestamp) throws InterruptedException {
        String data = String.valueOf(timestamp) + "_PRODUCED_BY_" + name;
        queue.enqueue(data);
    }

    public void setProductionDelay(int productionDelay) {
        this.productionDelay = productionDelay;
    }

    public void setProductionsCount(int productionsCount) {
        this.productionsCount = productionsCount;
    }
}
