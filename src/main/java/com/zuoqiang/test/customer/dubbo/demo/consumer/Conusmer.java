package com.zuoqiang.test.customer.dubbo.demo.consumer;

import com.zuoqiang.test.customer.dubbo.demo.framework.Proxy;
import com.zuoqiang.test.customer.dubbo.demo.provider.api.HelloService;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/13 10:10 上午
 */

public class Conusmer {
    public static void main(String[] args) {
        HelloService proxy = Proxy.getProxy(HelloService.class);
        String result = proxy.hello("zzz");
        System.out.println(result);
    }
}
