package com.zuoqiang.test.io.netty;

import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description RetryTimerTask
 * @date 2020/6/17 1:51 下午
 */

@Slf4j
public class RetryTimerTask implements TimerTask {
    // 每隔几秒执行一次
    private final long tick;

    // 最大重试次数
    private final int retries;

    /**
     * 当前重试次数
     */
    private int retryTimes = 0;

    private Runnable task;

    public RetryTimerTask(Runnable task, long tick, int retries) {
        this.tick = tick;
        this.retries = retries;
        this.task = task;
    }


    @Override
    public void run(Timeout timeout) throws Exception {
        try {
            task.run();
        } catch (Throwable e) {
            if ((++retryTimes) >= retries) {
                // 重试次数超过了设置的值
                log.error("失败重试次数超过阈值: {}，不再重试", retries);
            } else {
                log.error("重试失败，继续重试");
                rePut(timeout);
            }
        }
    }

    /**
     * 通过 timeout 拿到 timer 实例，重新提交一个定时任务
     *
     * @param timeout
     */
    private void rePut(Timeout timeout) {
        if (timeout == null) {
            return;
        }
        Timer timer = timeout.timer();
        if (timeout.isCancelled()) {
            return;
        }
        timer.newTimeout(timeout.task(), tick, TimeUnit.SECONDS);
    }
}
