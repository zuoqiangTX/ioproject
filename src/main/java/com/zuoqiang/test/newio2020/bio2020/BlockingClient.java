package com.zuoqiang.test.newio2020.bio2020;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 客户端 SocketChannel 的使用 阻塞
 * @date 2020/6/30 3:32 下午
 */

@Slf4j
public class BlockingClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        // 发送请求
        ByteBuffer buffer = ByteBuffer.wrap("1234567890".getBytes());
        socketChannel.write(buffer);
        // 读取响应
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int num;
        if ((num = socketChannel.read(readBuffer)) > 0) {
            readBuffer.flip();
            byte[] content = new byte[num];
            //将内容从buffer里面读到字节数组里面
            readBuffer.get(content);
            String result = new String(content, "UTF-8");
            log.info("客户端收到的内容为:{}", result);
        }
    }
}
