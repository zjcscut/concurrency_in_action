package org.throwable.blocking;

import java.io.IOException;

/**
 * @author zjc
 * @version 2017/1/12 23:52
 * @description
 */
public class Main {

	public static void main(String[] args) throws InterruptedException,IOException {
		ArrayBlockingQueueThread runnable = new ArrayBlockingQueueThread();
		Thread thread = new Thread(runnable, "queueThread");
		thread.start();


		Thread.sleep(2000);
        runnable.submitTask(new Task(1,"zjc1Task"));
        runnable.submitTask(new Task(2,"zjc2Task"));
        runnable.submitTask(new Task(3,"zjc3Task"));
		Thread.sleep(2000);
        runnable.init();

        System.in.read();
	}
}
