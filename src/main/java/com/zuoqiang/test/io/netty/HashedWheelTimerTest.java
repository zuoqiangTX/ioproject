package com.zuoqiang.test.io.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description HashedWheelTimer 时间轮
 * @date 2020/6/17 11:46 上午
 */

public class HashedWheelTimerTest {
    public static void main(String[] args) {
        // 构造一个 Timer 实例
        Timer timer = new HashedWheelTimer();
        Timeout timeout1 = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("5s 后执行该任务");
            }
        }, 5, TimeUnit.SECONDS);
        Timeout timeout2 = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("10s 后执行该任务");
            }
        }, 10, TimeUnit.SECONDS);
        //取消掉那个 5s 后执行的任务
        if (!timeout2.isExpired()) {
            timeout2.cancel();
        }
        // 原来那个 5s 后执行的任务，已经取消了。这里我们反悔了，我们要让这个任务在 3s 后执行
// 我们说过 timeout 持有上、下层的实例，所以下面的 timer 也可以写成 timeout1.timer()
        timer.newTimeout(timeout2.task(), 3, TimeUnit.SECONDS);
    }
}
