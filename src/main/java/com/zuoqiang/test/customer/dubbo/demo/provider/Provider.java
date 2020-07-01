package com.zuoqiang.test.customer.dubbo.demo.provider;

import com.zuoqiang.test.customer.dubbo.demo.framework.URL;
import com.zuoqiang.test.customer.dubbo.demo.protocol.dubbo.NettyServer;
import com.zuoqiang.test.customer.dubbo.demo.provider.api.HelloService;
import com.zuoqiang.test.customer.dubbo.demo.provider.impl.HelloServiceImpl;
import com.zuoqiang.test.customer.dubbo.demo.register.RemoteRegister;

import java.io.IOException;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/13 10:11 上午
 */

public class Provider {
    public static void main(String[] args) throws IOException {
        //本地注册服务【服务名：注册类】
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        //远程注册服务
//        服务名:url列表
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);

        //启动应用[保证应用不关闭]
        NettyServer server = new NettyServer();
        server.start(url.getHostName(), url.getPort());
        System.in.read();
    }
}
