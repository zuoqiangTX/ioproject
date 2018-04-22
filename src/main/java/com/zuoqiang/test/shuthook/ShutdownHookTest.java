package com.zuoqiang.test.shuthook;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 ShutdownHook
 * 1）直接杀掉程序; 2)正常退出的时候; 3)抛出异常的时候
 *
 * @author zuoqiang
 */

public class ShutdownHookTest {
    /**
     * 定时器
     */
    static Timer timer = new Timer("job_time");

    /**
     * 统计次数
     */
    static AtomicInteger count = new AtomicInteger(0);

    /**
     * 钩子hook线程
     */
    static class CleanThread extends Thread {
        @Override
        public void run() {
            System.out.println("CLEAN SOME WORK.");
            timer.cancel();
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //将钩子注册到运行时环境中去
        Runtime.getRuntime().addShutdownHook(new CleanThread());
        System.out.println("MAIN CLASS START......");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count.getAndIncrement();
                System.out.println("doing job" + count);
                if (count.get() == 10) {
                    //int a = 1 / 0;
                    //退出程序
                    System.exit(0);
                }
            }
        }, 0, 2 * 1000);
    }
}
