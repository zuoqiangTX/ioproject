package com.zuoqiang.test.dubbo.spi;

import com.zuoqiang.test.dubbo.spi.api.Car;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.io.IOException;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/14 4:04 下午
 */

public class CarDemo {
    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car redCar = extensionLoader.getExtension("red");
        redCar.getColor();
        Car black = extensionLoader.getExtension("black");
        black.getColor();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
