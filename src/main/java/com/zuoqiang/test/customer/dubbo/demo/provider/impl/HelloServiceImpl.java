package com.zuoqiang.test.customer.dubbo.demo.provider.impl;

import com.zuoqiang.test.customer.dubbo.demo.provider.api.HelloService;

/**
 * @author zuoqiang
 * @version 1.0
 * @description HelloService实现类
 * @date 2020/5/13 10:15 上午
 */

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello, " + name;
    }
}
