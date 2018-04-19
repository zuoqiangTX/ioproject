package com.zuoqiang.test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * FileChannel文件通道
 * 通过文件通道可以读、写文件的数据,只能在阻塞模式下运行，不能非阻塞。
 * @author zuoqiang
 */
public class FileChannelTest {
    public static int BYTE_BUFFER_SIZE = 48;

    public static void main(String[] args) throws IOException {
        //1.打开文件通道
        RandomAccessFile file = new RandomAccessFile("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/nio.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        System.out.println("文件channel的大小为:" + fileChannel.size());
        //截取文件大小
        fileChannel.truncate(43);

        //2.从文件通道内读数据，建立一个Buffer,read代表写入多少字节，-1代表读到文件尾部
        ByteBuffer buffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
        int bytesRead = fileChannel.read(buffer);
        System.out.println("第一次读到的数据为" + bytesRead);
        while (bytesRead != -1) {
            //一定要设置为可读模式，不然会乱码！！！！
            //flip()方法可以吧Buffer从写模式切换到读模式。调用flip方法会把position归零，并设置limit为之前的position的值。
            // 也就是说，现在position代表的是读取位置，limit标示的是已写入的数据位置
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();  //清空数据；
            bytesRead = fileChannel.read(buffer);
        }

        //3向文件通道写入数据
        String newData = "New String to write to file...\n" + System.currentTimeMillis();
        ByteBuffer byteWriteBuffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
        int newDataSize = newData.getBytes().length;
        if (newDataSize > BYTE_BUFFER_SIZE) {
            System.out.println("buffer区太小，无法写入");
        } else {
            byteWriteBuffer.put(newData.getBytes());
            System.out.println("写入成功！");
        }
        //切换为读模式
        byteWriteBuffer.flip();
        while (byteWriteBuffer.hasRemaining()) {
            fileChannel.write(byteWriteBuffer);
        }

        //4.关闭通道
        fileChannel.close();
    }

}
