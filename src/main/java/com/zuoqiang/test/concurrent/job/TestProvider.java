package com.zuoqiang.test.concurrent.job;

import com.zuoqiang.test.concurrent.threadPool.AbstractTaskProvider;
import com.zuoqiang.test.concurrent.threadPool.EnhanceCompletionService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 线程提供器
 * @date 2020/8/25 10:06 上午
 */

@Slf4j
public class TestProvider extends AbstractTaskProvider {
    public TestProvider(EnhanceCompletionService ecs) {
        super(ecs);
    }

    @Override
    public void offerTasks() {
        for (int i = 0; i < 500; i++) {
            ecs.submit(new TestTask(i));
        }
    }

    class TestTask implements Callable {
        private int anInt;

        public TestTask(int anInt) {
            this.anInt = anInt;
        }

        @Override
        public Object call() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("任务{}执行完毕", anInt);
                return true;
            } catch (InterruptedException e) {
                log.error("任务{}执行失败", anInt, e);
            }
            return false;
        }
    }
}
