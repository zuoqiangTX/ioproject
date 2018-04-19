package com.zuoqiang.test.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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
        // Channel必须是非阻塞的。所以FileChannel不适用Selector，因为FileChannel不能切换为非阻塞模式,Socketchannel可以正常使用。
        SocketChannel socketChannel = null;

        //切换为非阻塞模式
        socketChannel.configureBlocking(false);

       /*  select有四种状态,代表我们关注【注册的channel】的哪种状态
         OP_CONNECT 连接就绪
         OP_READ    读就绪
         OP_WRITE   写就绪
         OP_ACCEPT  接收就绪*/
        //channel注册到上面去，并且写明自己关注channel的哪种状态
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);

        //SelectionKey返回值包含了一些比较有意思的属性
        while (true) {
            //select方法会返回所有处于就绪状态的channel。
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            //获取channel集合并判断状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for reading
                }
            }
            iterator.remove();
        }


    }
}
