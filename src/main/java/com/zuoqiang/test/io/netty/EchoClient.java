package com.zuoqiang.test.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        //处理创建连接、入站事件和出站事件
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //该处理器会被加入socketChannel的处理链中
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //阻塞直到连接到远程节点完成
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞直到关闭
            future.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("使用：" + EchoClient.class.getSimpleName() + "<host> <port>");
        }
        String host = args[0];
        Integer port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
}
