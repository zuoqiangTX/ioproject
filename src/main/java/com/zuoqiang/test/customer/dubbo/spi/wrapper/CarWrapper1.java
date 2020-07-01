package com.zuoqiang.test.customer.dubbo.spi.wrapper;

import com.zuoqiang.test.customer.dubbo.spi.api.Car;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/14 4:10 下午
 */

public class CarWrapper1 implements Car {
    private Car car;

    public CarWrapper1(Car car) {
        this.car = car;
    }

    @Override
    public void getColor() {
        System.out.println("before-1");
        car.getColor();
        System.out.println("after-1");
    }
}
