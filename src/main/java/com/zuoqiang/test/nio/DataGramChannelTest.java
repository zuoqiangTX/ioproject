package com.zuoqiang.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/***
 * DatagramChannel数据报通道
 * Java NIO DatagramChannel是一个可以发送、接收UDP数据包的通道。
 * 由于UDP是面向无连接的网络协议，我们不可用像使用其他通道一样直接进行读写数据。正确的做法是发送、接收数据包。
 * @author tongbanjie
 */

public class DataGramChannelTest {
    public static void main(String[] args) throws IOException {
        //1、打开通道
        DatagramChannel channel = DatagramChannel.open();
        channel.connect(new InetSocketAddress(9999));
        //链接特定机器地址  锁定DatagramChannel,这样我们就只能通过特定的地址来收发数据包。
        //channel.connect(new InetSocketAddress("jenkov.com", 80));


        //2.1、接收数据 receive()方法会把接收到的数据包中的数据拷贝至给定的Buffer中。如果数据包的内容超过了Buffer的大小，剩余的数据会被直接丢弃。
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        channel.receive(buffer);

        //2.2、发送数据  通过DatagramChannel的send()方法
        String newData = "New String to wrte to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        channel.send(buf, new InetSocketAddress("jenkov.com", 80));
    }
}
