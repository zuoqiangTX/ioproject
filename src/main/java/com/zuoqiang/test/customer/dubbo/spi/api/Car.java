package com.zuoqiang.test.customer.dubbo.spi.api;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 接口
 * @date 2020/5/14 4:00 下午
 */

@SPI
public interface Car {
    void getColor();
}
