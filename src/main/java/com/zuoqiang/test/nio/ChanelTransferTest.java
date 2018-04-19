package com.zuoqiang.test.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/***
 * Java NIO Channel to Channel Transfers通道传输接口
 *
 * 在Java NIO中如果一个channel是FileChannel类型的，那么他可以直接把数据传输到另一个channel。
 * 这个特性得益于FileChannel包含的transferTo和transferFrom两个方法。
 */
public class ChanelTransferTest {
    public static void main(String[] args) throws Exception {
        ChanelTransferTest test = new ChanelTransferTest();
        //test.transFrom();
        test.transTo();

    }

    public void transFrom() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/a.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        //写入位置，最多写入量
        toChannel.transferFrom(fromChannel, position, count);
        System.out.println("写入成功！");
    }

    public void transTo() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = toChannel.size();
        toChannel.transferTo(position, count, fromChannel);

    }
}
