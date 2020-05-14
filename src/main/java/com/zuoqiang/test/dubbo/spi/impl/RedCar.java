package com.zuoqiang.test.dubbo.spi.impl;

import com.zuoqiang.test.dubbo.spi.api.Car;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/14 4:02 下午
 */

public class RedCar implements Car {
    @Override
    public void getColor() {
        System.out.println("red");
    }
}
