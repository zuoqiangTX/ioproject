package com.zuoqiang.test.dubbo.ext;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/12 5:08 下午
 */

@Slf4j
public class DubboPropetiesTest {
    public static void main(String[] args) {
        DubboRegistry dubboRegistry = new DubboRegistry();
        Properties cacheProperties = dubboRegistry.getCacheProperties();
        System.out.println();
        // 设置到 properties 中
        cacheProperties.setProperty("key", "v");
        // 增加数据版本号
        long version = 0;
        dubboRegistry.doSaveProperties(0);

    }


}
