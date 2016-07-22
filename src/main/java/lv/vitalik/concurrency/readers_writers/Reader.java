package lv.vitalik.concurrency.readers_writers;

import lv.vitalik.concurrency.readers_writers.shared_resource.Resource;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public class Reader extends Thread {
    private String name;
    private Resource resource;

    public Reader(String name, Resource resource) {
        super(name);
        this.name = name;
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.read(name);
    }
}
