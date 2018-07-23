package com.zuoqiang.test.threadPool;

/**
 * 默认的结果处理器
 */
public class DefaultResultConsumer extends AbstractResultConsumer<Boolean, Counter> {

    public DefaultResultConsumer(Counter counter) {
        super(counter);
    }

    @Override
    public void consume(Boolean isSuccess) {

        s.allPlus();

        if (isSuccess) {
            s.successPlus();
        } else {
            s.failPlus();
        }
    }

    @Override
    public Counter getResult() {
        return s;
    }
}
