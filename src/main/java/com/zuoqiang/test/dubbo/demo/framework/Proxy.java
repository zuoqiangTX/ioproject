package com.zuoqiang.test.dubbo.demo.framework;

import com.zuoqiang.test.dubbo.demo.protocol.Invocation;
import com.zuoqiang.test.dubbo.demo.protocol.http.HttpClient;
import com.zuoqiang.test.dubbo.demo.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/13 11:36 上午
 */

public class Proxy {
    public static <T> T getProxy(Class<T> interfaceClass) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        HttpClient httpClient = new HttpClient();
                        Invocation invocation = new Invocation(interfaceClass.getName(),
                                method.getName(),
                                method.getParameterTypes(),
                                args);
                        URL url = RemoteRegister.getRandom(interfaceClass.getName());
                        String result = httpClient.send(url.getHostName(), url.getPort(), invocation);
                        return result;
                    }
                });
    }
}
