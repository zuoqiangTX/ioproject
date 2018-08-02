package com.zuoqiang.test.socket.netty.discard;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty discard
 *
 * @author zuoqiang
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        //一个worker线程组发起连接
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        //绑定线程组
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
        //发数据
        //buf,利用缓冲区去写
        cf1.channel().write(Unpooled.copiedBuffer("777".getBytes()));
        cf1.channel().flush();

        //类似主程序阻塞先不停止
        cf1.channel().closeFuture().sync();
        workGroup.shutdownGracefully();


    }
}
