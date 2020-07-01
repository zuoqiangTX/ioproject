package com.zuoqiang.test.io.socket.netty.serial;

import com.zuoqiang.test.tools.until.GzipUtils;
import com.zuoqiang.test.tools.until.PathConstans;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

/**
 * Marshalling编、解码器的使用
 *
 * @author zuoqiang
 */
public class Client {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();

        for (int i = 0; i < 5; i++) {
            Req req = new Req();
            req.setId("" + i);
            req.setName("pro" + i);
            req.setRequestMessage("数据信息" + i);

            String path = System.getProperty("user.dir") + PathConstans.myObjectchar + File.separatorChar + "sources" + File.separatorChar + "001.jpg";
            FileInputStream inputStream = new FileInputStream(new File(path));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            req.setAttachment(GzipUtils.gzip(data));
            cf.channel().writeAndFlush(req);
        }

        cf.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
