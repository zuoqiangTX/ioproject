package com.zuoqiang.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/***
 * ServerSocketChannel服务端套接字通道
 * ServerSocketChannel是用于监听TCP链接请求的通道，正如Java网络编程中的ServerSocket一样.
 * @author baiyue
 */

public class ServerSocketChannelTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        //阻塞模式
        BlockingMode(serverSocketChannel);
        //非阻塞模式
        noBlockingMode(serverSocketChannel);
    }

    public static void BlockingMode(ServerSocketChannel serverSocketChannel) throws IOException {
        //设置为阻塞模式，默认为true
        serverSocketChannel.configureBlocking(true);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            //dosomething with sockerChannel
        }
    }

    public static void noBlockingMode(ServerSocketChannel serverSocketChannel) throws IOException {
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            //非阻塞模式,调用accept()函数会立刻返回，如果当前没有请求的链接，那么返回值为空null.因此需要判空
            if (socketChannel != null) {
                //dosomething with sockerChannel
            }
        }
    }
}
