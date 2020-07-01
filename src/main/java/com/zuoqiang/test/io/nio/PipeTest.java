package com.zuoqiang.test.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Pipe管道
 * 一个Java NIO的管道是两个线程间单向传输数据的连接。
 * 写入数据--->sink channel    source channel--->读取数据
 * @author baiyue
 */
public class PipeTest {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        //向管道写入数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer inBuffer = ByteBuffer.allocate(48);
        inBuffer.put(newData.getBytes());
        inBuffer.flip();
        while (inBuffer.hasRemaining()) {
            sinkChannel.write(inBuffer);
        }

        //从管道读出数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer outBuffer = ByteBuffer.allocate(48);
        int readBuffer = sourceChannel.read(outBuffer);

    }
}
