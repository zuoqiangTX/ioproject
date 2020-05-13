package com.zuoqiang.test.dubbo.demo.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 本地缓存
 * @date 2020/5/13 10:17 上午
 */

public class LocalRegister {
    //服务名:实现类
    private static Map<String, Class> LOCAL_REGISTER = new ConcurrentHashMap<>();

    public static void register(String interfaceName, Class classImpl) {
        LOCAL_REGISTER.putIfAbsent(interfaceName, classImpl);
    }

    public static Class get(String interfaceName) {
        return LOCAL_REGISTER.get(interfaceName);
    }
}
