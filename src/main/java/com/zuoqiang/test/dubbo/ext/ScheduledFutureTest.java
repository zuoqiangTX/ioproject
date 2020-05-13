package com.zuoqiang.test.dubbo.ext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description ScheduledFuture
 * @date 2020/5/12 5:58 下午
 * <p>
 * 它设置了 ScheduledExecutorService ，在 6秒内每 1 秒钟蜂(beep)鸣一次
 * </p>
 */

public class ScheduledFutureTest {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };
        //0延时，每1秒执行下beeper的任务
        final ScheduledFuture<?> beeperHandler = scheduler.scheduleAtFixedRate(beeper, 0, 1, TimeUnit.SECONDS);
        //执行6秒后，取消beeperHandler任务的执行并退出程序
        scheduler.schedule(() -> {
            beeperHandler.cancel(true);
            System.exit(0);
        }, 6, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new ScheduledFutureTest().beepForAnHour();
    }

}
