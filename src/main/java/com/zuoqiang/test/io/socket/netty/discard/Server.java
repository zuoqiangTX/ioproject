package com.zuoqiang.test.io.socket.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty discard
 *
 * @author zuoqiang
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        //TCP协议
        //1.boss 第一个线程组是用于接收client端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2.worker 第二个线程组是用于实际的业务处理操作的
        EventLoopGroup workderGroup = new NioEventLoopGroup();

        //3.创建辅助类BootSrrap对我们的Server进行一系列配置
        try {
            ServerBootstrap b = new ServerBootstrap();
            //把两个线程组加入进来
            b.group(bossGroup, workderGroup)
                    //需要指定NioServerSocketChannel这种类型的通道
                    .channel(NioServerSocketChannel.class)
                    //一定要使用ChannelHandler来绑定具体的类型处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    })
                    //默认128 backlog设置TCP连接缓冲区（A+B队列之和）
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //保持连接 配置上面的通道
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定指定的端口 进行监听 异步
            ChannelFuture f = b.bind(8765).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("Server在端口{}处理异常,err =", 8765, e);
        } finally {
            workderGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}
