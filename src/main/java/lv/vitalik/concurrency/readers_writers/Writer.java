package lv.vitalik.concurrency.readers_writers;

import lv.vitalik.concurrency.readers_writers.shared_resource.Resource;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public class Writer extends Thread {
    private String name;
    private String text;
    private Resource resource;

    public Writer(String name, String text, Resource resource) {
        super(name);
        this.name = name;
        this.text = text;
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.write(name, text);
    }
}
