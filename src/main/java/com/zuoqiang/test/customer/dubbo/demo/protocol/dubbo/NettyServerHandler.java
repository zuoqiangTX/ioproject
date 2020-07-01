package com.zuoqiang.test.customer.dubbo.demo.protocol.dubbo;

import com.zuoqiang.test.customer.dubbo.demo.protocol.Invocation;
import com.zuoqiang.test.customer.dubbo.demo.provider.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author tanghf
 * @className protocal.dubbo.NettyServerHandler.java
 * @createTime 2019/8/23 10:22
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        Class implClass = LocalRegister.get(invocation.getInterfaceName());
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamsType());
        String invoke = (String) method.invoke(implClass, invocation.getValues());
        System.out.println("Netty===============" + invoke);
        ctx.writeAndFlush("Netty: " + invoke);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
