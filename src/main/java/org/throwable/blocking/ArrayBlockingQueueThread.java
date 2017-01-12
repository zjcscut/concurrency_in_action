package org.throwable.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zjc
 * @version 2017/1/12 23:53
 * @description
 */
public class ArrayBlockingQueueThread implements Runnable {

	private final BlockingQueue<Task> tasks =
			new ArrayBlockingQueue<>(100);


	private volatile boolean flag = false;

	@Override
	public void run() {
		Task task;
		while (true) {
			try {
				task = tasks.take();
				doProcess(task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void submitTask(Task task) {
		try {
			tasks.put(task);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}

	public void init() {
		flag = true;
	}

	public void stop() {
		flag = false;
	}

	private void doProcess(Task task) {
		System.out.println("doing process!!!;Task name : " + task.getName());
	}
}
