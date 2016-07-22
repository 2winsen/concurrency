package lv.vitalik.concurrency.readers_writers.shared_resource;

/**
 * Created by Vitalijs on 28-Jun-16.
 */
public interface Resource {
    public void read(String name);
    public void write(String name, String appendedText);
}
