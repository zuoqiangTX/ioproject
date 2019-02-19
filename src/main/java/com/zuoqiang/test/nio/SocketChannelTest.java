package com.zuoqiang.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/***
 * SocketChannel套接字通道
 * SocketChannel是用于TCP网络连接的套接字接口,相当于Java网络编程中的Socket套接字接口。
 * @author baiyue
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        //1、打开SocketChannel连接
        SocketChannel socketChannel = SocketChannel.open();
        //设置为异步 非阻塞模式
        //socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://www.baidu.com", 80));
        while (!socketChannel.finishConnect()) {
            //wait, or do something else...  非异步模式下检查当前连接是否就绪
        }

        //2.1从SocketChannel取数据(新建一个Buffer,将Socket读入Buffer)
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int byteRead = socketChannel.read(buffer);

        //2.2从SocketChannel写数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer inBuffer = ByteBuffer.allocate(48);
        inBuffer.clear();
        inBuffer.put(newData.getBytes());
        inBuffer.flip();
        while (inBuffer.hasRemaining()) {
            socketChannel.write(inBuffer);
        }


        //关闭SocketChannel
        socketChannel.close();

    }
}
