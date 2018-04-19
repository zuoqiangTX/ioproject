package com.zuoqiang.test.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/***
 * Java NIO Selector选择器
 *
 * 通过Selector我们可以实现单线程操作多个channel。
 *
 * @author zuoqiang
 */
public class SelectsTest {
    public static void main(String[] args) throws Exception {
        //创建Selector
        Selector selector = Selector.open();
        //channel注册到上面去.
        // Channel必须是非阻塞的。所以FileChannel不适用Selector，因为FileChannel不能切换为非阻塞模式。
        // Socket channel可以正常使用。
        SocketChannel socketChannel = null;
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);


    }
}
