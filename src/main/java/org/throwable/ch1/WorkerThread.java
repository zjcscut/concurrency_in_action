package org.throwable.ch1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhangjinci
 * @version 2017/1/11 16:08
 * @function
 */
public class WorkerThread {

    public static void main(String[] args){
         Helper helper = new Helper();
         helper.submit("zjcscut");
         helper.init();
    }

    static class Helper{
        private final BlockingQueue<String> workQueue =
                new ArrayBlockingQueue<>(100);

        private final Thread workerThread = new Thread(){

            @Override
            public void run() {
                String task;
                while (true){
                    try {
                        task = workQueue.take();
                    }catch (InterruptedException e){
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        public void init(){
            workerThread.start();
        }

        protected String doProcess(String task){
            return task  + "->doProcess!";
        }

        public void submit(String task){
            try {
                workQueue.put(task);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }
}
