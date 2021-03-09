package com.zuoqiang.test.id;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Id生成器接口
 * @date 2021/3/9 3:37 下午
 */

public interface IdentifierGenerator {

    /**
     * 生成Id
     *
     * @param entity 实体
     * @return id
     */
    Number nextId(Object entity);

    /**
     * 生成uuid
     *
     * @param entity 实体
     * @return uuid
     */
    default String nextUUID(Object entity) {
        return IdWorker.get32UUID();
    }
}
