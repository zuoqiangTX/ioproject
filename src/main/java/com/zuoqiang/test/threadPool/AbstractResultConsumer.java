package com.zuoqiang.test.threadPool;

/**
 * @param <V> 单条数据处理结果类型
 * @param <S> 所有V的汇总结果
 */
public abstract class AbstractResultConsumer<V, S> {
    protected S s;

    public AbstractResultConsumer(S s) {
        this.s = s;
    }

    /**
     * 消费处理单条结果，将单条任务的结果汇总到总结果对象上
     *
     * @param v 单个任务的执行结果
     */
    public abstract void consume(V v);

    public S getResult() {
        return s;
    }
}
