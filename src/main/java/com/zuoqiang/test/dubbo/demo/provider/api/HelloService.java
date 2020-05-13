package com.zuoqiang.test.dubbo.demo.provider.api;

/**
 * @author zuoqiang
 * @version 1.0
 * @description HelloService
 * @date 2020/5/13 10:14 上午
 */

public interface HelloService {
    /**
     * 打招呼
     *
     * @param name
     * @return
     */
    String hello(String name);
}
