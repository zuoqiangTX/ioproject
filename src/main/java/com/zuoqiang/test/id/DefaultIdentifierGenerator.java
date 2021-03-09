package com.zuoqiang.test.id;


/**
 * @author zuoqiang
 * @version 1.0
 * @description 默认生成器
 * @date 2021/3/9 3:38 下午
 */

public class DefaultIdentifierGenerator implements IdentifierGenerator {

    private final Sequence sequence;

    public DefaultIdentifierGenerator() {
        this.sequence = new Sequence();
    }

    public DefaultIdentifierGenerator(long workerId, long dataCenterId) {
        this.sequence = new Sequence(workerId, dataCenterId);
    }

    public DefaultIdentifierGenerator(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public Long nextId(Object entity) {
        return sequence.nextId();
    }
}
