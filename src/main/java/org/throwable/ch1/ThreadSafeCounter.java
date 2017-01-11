package org.throwable.ch1;

/**
 * @author zhangjinci
 * @version 2017/1/11 16:15
 * @function
 */
public class ThreadSafeCounter {

    private int count = 0;

    public void increace() {
        synchronized (this) {
            count++;
        }
    }

    public int get() {
        synchronized (this) {
            return count;
        }
    }
}
