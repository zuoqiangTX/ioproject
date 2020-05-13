package com.zuoqiang.test.dubbo.demo.protocol.dubbo;

import com.zuoqiang.test.dubbo.demo.protocol.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;

import java.util.concurrent.Callable;

/**
 * @author tanghf
 * @className protocal.dubbo.NettyClientHandler.java
 * @createTime 2019/8/23 10:22
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private Invocation invocation;
    private Future future;

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        future = ctx.writeAndFlush(invocation);
    }

    @Override
    public Object call() throws Exception {
        return future;
    }
}
