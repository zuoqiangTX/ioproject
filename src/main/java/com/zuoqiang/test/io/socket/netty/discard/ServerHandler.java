package com.zuoqiang.test.io.socket.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty discard
 * 服务器处理类
 *
 * @author zuoqiang
 */
public class ServerHandler extends ChannelHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //        ((ByteBuf) msg).release();
        try {
            //do something
            ByteBuf buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            String request = new String(bytes, "utf-8");
            LOGGER.info("服务器端收到" + request);

            //写给客户端
            String respsone = "888";
            ctx.writeAndFlush(Unpooled.copiedBuffer(respsone.getBytes()))
                    //发完主动断开TCP连接
                    .addListener(ChannelFutureListener.CLOSE);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
