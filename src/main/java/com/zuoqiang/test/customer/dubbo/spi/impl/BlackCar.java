package com.zuoqiang.test.customer.dubbo.spi.impl;

import com.zuoqiang.test.customer.dubbo.spi.api.Car;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/14 4:03 下午
 */

public class BlackCar implements Car {
    @Override
    public void getColor() {
        System.out.println("black");
    }
}
