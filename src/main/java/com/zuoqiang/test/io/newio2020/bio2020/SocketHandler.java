package com.zuoqiang.test.io.newio2020.bio2020;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 网络通讯任务线程
 * @date 2020/6/30 3:26 下午
 */

@Slf4j
public class SocketHandler implements Runnable {
    private SocketChannel socketChannel;

    public SocketHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int num;
        try {
            while ((num = socketChannel.read(buffer)) > 0) {
                // 读取 Buffer 内容之前先 flip 一下
                buffer.flip();
                // 提取 Buffer 中的数据
                byte[] bytes = new byte[num];
                buffer.get(bytes);
                String content = new String(bytes, "UTF-8");
                log.info("服务端收到请求:{}", content);
                ByteBuffer writeBuffer = ByteBuffer.wrap(("我已经收到你的请求，你的请求内容是：" + content).getBytes());
                socketChannel.write(writeBuffer);
                buffer.clear();
            }
        } catch (IOException e) {
            IOUtils.closeQuietly(socketChannel);
        }

    }
}
