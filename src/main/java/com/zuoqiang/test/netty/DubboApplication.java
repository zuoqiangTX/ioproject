package com.zuoqiang.test.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 代码修改自 Dubbo 的集群调用策略 FailbackClusterInvoker
 * <p>
 * 在调用 doInvoke() 方法失败以后，提交一个定时任务在 5s 后执行重试，如果还是失败，
 * 之后每 3s 重试一次，最多重试 5 次，
 * 如果重试 5 次都失败，记录错误日志，不再重试。
 * </P>
 * @date 2020/6/17 1:49 下午
 */

@Slf4j
public class DubboApplication {
    private volatile Timer failTimer = null;

    public static void main(String[] args) {
        DubboApplication app = new DubboApplication();
        app.invoke();
    }

    private void invoke() {
        try {
            doInvoke();
        } catch (Throwable e) {
            log.error("调用 doInvoke 方法失败，5s 后将进入后台的自动重试，异常信息: ", e);
            addFailed(() -> doInvoke());
        }
    }

    // 实际的业务实现
    private void doInvoke() {
        // 这里让这个方法故意失败
        throw new RuntimeException("故意抛出异常");
    }

    private void addFailed(Runnable task) {
        // 延迟初始化
        if (failTimer == null) {
            synchronized (this) {
                if (failTimer == null) {
                    failTimer = new HashedWheelTimer();
                }
            }
        }
        RetryTimerTask retryTimerTask = new RetryTimerTask(task, 3, 5);
        try {
            // 5s 后执行第一次重试
            failTimer.newTimeout(retryTimerTask, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            log.error("提交定时任务失败，exception: ", e);
        }
    }
}
