package com.zuoqiang.test.io.newio2020.bio2020;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 阻塞模式 IO
 * @date 2020/6/30 3:24 下午
 */

public class BlockingServer {
    public static void main(String[] args) throws IOException {
        AtomicInteger integer = new AtomicInteger();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 监听 8080 端口进来的 TCP 链接
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        while (true) {
            // 这里会阻塞，直到有一个请求的连接进来
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 开启一个新的线程来处理这个请求，然后在 while 循环中继续监听 8080 端口
            SocketHandler handler = new SocketHandler(socketChannel);
            new Thread(handler, "阻塞id线程" + integer.getAndIncrement()).start();
        }
    }
}
