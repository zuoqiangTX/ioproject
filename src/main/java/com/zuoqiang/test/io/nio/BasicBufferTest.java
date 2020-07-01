package com.zuoqiang.test.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * Java NIO Buffer缓冲区
 * Java NIO Buffers用于和NIO Channel交互。我们从channel中读取数据到buffers里，从buffer把数据写入到channels.
 * buffer本质上就是一块内存区，可以用来写入数据，并在稍后读取出来。这块内存被NIO Buffer包裹起来，对外提供一系列的读写方便开发的接口
 */
public class BasicBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/a.txt", "rw");
        FileChannel channel = file.getChannel();
        //创建缓冲区大小,分配buffer内存
        ByteBuffer buffer = ByteBuffer.allocate(48);
        //写buffer两种方法，1）从channel中读取；2）put手动写
        buffer.put(("YJH\n").getBytes()); //如果超出空间，抛出BufferOverflowException
        int bytesRead = channel.read(buffer);  //此方法会将buffer剩余空间读完。

        while (bytesRead != -1) {
            //转为可读模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            //一旦buffer写满了就需要清空已读数据以便下次继续写入新的数据。
            //buffer.clear();
            buffer.compact();  //只是清空已读数据；
            bytesRead = channel.read(buffer);
        }
        file.close();
    }
}
