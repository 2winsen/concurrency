package lv.vitalik.concurrency.dining_philosophers;

import java.util.concurrent.Semaphore;

/**
 * Created by Vitalijs on 21-Jul-16.
 */
public class Fork extends Semaphore {
    private int index;
    public static final int PERMITS = 1;

    public Fork(int index) {
        super(PERMITS);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
