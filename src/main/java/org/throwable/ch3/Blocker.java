package org.throwable.ch3;

import java.util.concurrent.Callable;

/**
 * @author zhangjinci
 * @version 2017/1/12 18:17
 * @function
 */
public interface Blocker {

    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOperation)throws Exception;

    void signal()throws InterruptedException;

    void broadcastAfter(Callable<Boolean> stateOperation)throws Exception;
}
