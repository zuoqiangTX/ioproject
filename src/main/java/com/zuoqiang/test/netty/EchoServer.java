package com.zuoqiang.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("使用" + EchoServer.class.getSimpleName() + "<port>");
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            //创建实例引导和绑定服务器
            ServerBootstrap bootstrap = new ServerBootstrap();
            //创建一个EventLoopGroup实例来进行事件的处理（接收新连接、读/写数据）
            bootstrap.group(loopGroup)
                    .channel(NioServerSocketChannel.class)
                    //指定服务器绑定本地的port
                    .localAddress(new InetSocketAddress(port))
                    //使用serverHandler实例化每一个新的Channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            //绑定服务器，sync将会一直阻塞直到完成
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully().sync();
        }

    }
}
