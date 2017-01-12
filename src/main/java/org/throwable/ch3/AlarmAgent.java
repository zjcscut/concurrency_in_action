package org.throwable.ch3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @author zhangjinci
 * @version 2017/1/12 18:45
 * @function
 */
public class AlarmAgent {

    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();

    private final Timer heartbeatTimer = new Timer(true);

    public void sendAlarm(final AlarmInfo alarmInfo) throws Exception {
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarmInfo);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    public void init() {
        // 省略其它代码

        // 告警连接线程
        Thread connectingThread = new Thread(new ConnectingTask());

        connectingThread.start();

        heartbeatTimer.schedule(new HeartbeatTask(), 1000, 1000);
    }

    // 通过网络连接将告警信息发送给告警服务器
    private void doSendAlarm(AlarmInfo alarm) {
        // 省略其它代码

        // 模拟发送告警至服务器的耗时
        try {
            Thread.sleep(500);
            System.out.println("正在进行发送操作");
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public void disconnect() {
        // 省略其它代码
        connectedToServer = false;
    }

    protected void onConnected() {
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    connectedToServer = true;
                    return Boolean.TRUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void onDisconnected() {
        connectedToServer = false;
    }

    private class ConnectingTask implements Runnable {

        @Override
        public void run() {
            // 模拟连接操作耗时
            try {
                Thread.sleep(100);
                System.out.println("正在进行连接操作");
            } catch (InterruptedException e) {
                ;
            }
            onConnected();
        }
    }

    /**
     * 心跳定时任务：定时检查与告警服务器的连接是否正常，发现连接异常后自动重新连接
     */
    private class HeartbeatTask extends TimerTask {
        // 省略其它代码

        @Override
        public void run() {
            // 省略其它代码

            if (!testConnection()) {
                onDisconnected();
                reconnect();
            }

        }

        private boolean testConnection() {
            // 省略其它代码

            return false;
        }

        private void reconnect() {
            ConnectingTask connectingThread = new ConnectingTask();

            System.out.println("正在执行重新连接");
            // 直接在心跳定时器线程中执行
            connectingThread.run();
        }
    }

    public static void main(String[] args) throws Exception {
        AlarmAgent agent = new AlarmAgent();
        agent.init();

        for (int i = 0; i < 100; i++) {
            agent.sendAlarm(new AlarmInfo());
        }


        Thread.sleep(2000);


        System.in.read();
    }
}
