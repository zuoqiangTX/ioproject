package com.zuoqiang.test.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * Java NIO Channel通道
 * Channel和流相似
 * 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
 * 通道总是基于缓冲区Buffer来读写。
 * 可以从通道中读取数据，写入到buffer；也可以中buffer内读数据，写入到通道中。
 */
public class BasicChnnelTest {
    public static void main(String[] args) throws Exception {
        //读数据到Buffer
        RandomAccessFile file = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/a.txt", "rw");
        FileChannel inChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        //把数据读到Buffer里面,大小为Buffer的大小48，可能一次读不完
        int bytesRead = inChannel.read(buffer);
        while (bytesRead != -1) {
            //System.out.println("Read is :" + bytesRead);
            //写模式-> 读模式，可以读取所有已经写入数据
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            //清空整个Buffer
            buffer.clear();
            //buffer.compact();  //只会清空已经读取的数据
            //继续读Channel里的内容
            bytesRead = inChannel.read(buffer);
        }
        file.close();

    }
}
