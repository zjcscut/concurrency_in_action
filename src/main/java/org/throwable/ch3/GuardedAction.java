package org.throwable.ch3;

import java.util.concurrent.Callable;

/**
 * @author zhangjinci
 * @version 2017/1/12 18:08
 * @function
 */
public abstract class GuardedAction<V> implements Callable<V>{

    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }


}
