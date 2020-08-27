package com.zuoqiang.test.concurrent.job;

import com.alibaba.fastjson.JSON;
import com.zuoqiang.test.concurrent.threadPool.Counter;
import com.zuoqiang.test.concurrent.threadPool.DefaultResultConsumer;
import com.zuoqiang.test.concurrent.threadPool.EnhanceCompletionService;
import com.zuoqiang.test.tools.until.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试job
 * @date 2020/8/25 10:04 上午
 */

@Slf4j
public class TestJob {

    private static ThreadPoolExecutor threadPool = null;

    private AtomicBoolean isStarted = new AtomicBoolean(false);

    static {
        threadPool = new ThreadPoolExecutor(4, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5000), new ThreadFactory() {
            private AtomicLong index = new AtomicLong(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("测试job线程-%s", index.getAndIncrement()));
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    log.error("Method[rejectedExecution] try put task to queue! occurs error = [" + e.getMessage() + "]",
                            e);
                }
            }
        });
        threadPool.allowCoreThreadTimeOut(true);
    }

    public void execute(String paramter) {
        if (paramter != null && StringUtils.equalsIgnoreCase("NORUN", StringUtils.trimToEmpty(paramter))) {
            log.info("测试job>>>>任务参数为NORUN, 不执行业务逻辑...");
            return;
        }
        log.info("测试job>>>>读取到的任务参数为{}", JSON.toJSONString(paramter));
//        if (config == null) {
//            log.info("测试job>>>>没有配置参数,终止执行，请先配置执行参数!!!");
//            return;
//        }
        if (!isStarted.compareAndSet(false, true)) {
            log.info("测试job>>>>上一个 JOB 还在执行...refuse now");
            return;
        }
        StopWatch stopWatch = StopWatch.createStarted();
        log.info("测试job>>>任务开始执行>>>>>>>>>>>>>>>start time:{}", DateUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        try {
            Counter counter = new Counter();
            DefaultResultConsumer defaultResultConsumer = new DefaultResultConsumer(counter);
            EnhanceCompletionService<Boolean, Counter> ecs =
                    new EnhanceCompletionService<Boolean, Counter>(threadPool, defaultResultConsumer);
            ecs.execute(new TestProvider(ecs));
            log.info("测试job>>>>>>执行总数量:{},成功数量:{},失败的数量为:{}", counter.getAll(),
                    counter.getSuccess(), counter.getFail());
        } catch (Exception e) {
            log.error("测试job>>>>任务执行异常,e", e);
        } finally {
            isStarted.compareAndSet(true, false);
        }
        log.info("测试job>>>>定时任务结束>>>>>>>>>>>>>>>end time:{},cost:{}s", DateUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss"),
                stopWatch.getTime(TimeUnit.SECONDS));
    }
}
