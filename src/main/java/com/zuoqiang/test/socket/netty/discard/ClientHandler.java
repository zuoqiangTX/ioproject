package com.zuoqiang.test.socket.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty discard
 * 客户端处理类
 *
 * @author zuoqiang
 */

public class ClientHandler extends ChannelHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

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
            LOGGER.info("客户端收到" + request);

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
