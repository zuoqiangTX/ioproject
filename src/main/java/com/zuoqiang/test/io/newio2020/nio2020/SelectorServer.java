package com.zuoqiang.test.io.newio2020.nio2020;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 非阻塞 IO 的核心在于使用一个 Selector 来管理多个通道
 * <p>
 * 之后可以只用一个线程来轮询这个 Selector，看看上面是否有通道是准备好的，当通道准备好可读或可写，
 * 然后才去开始真正的读写，这样速度就很快了。我们就完全没有必要给每个通道都起一个线程。
 * 1)select：它支持注册 FD_SETSIZE(1024) 个 socket，
 * 2)poll：poll 不再限制 socket 数量
 * 上面两种方式的缺点
 * 它们都只会告诉你有几个通道准备好了，但是不会告诉你具体是哪几个通道。所以，一旦知道有通道准备好以后，自己还是需要进行一次扫描，显然这个不太好，通道少的时候还行，一旦通道的数量是几十万个以上的时候，
 * 扫描一次的时间都很可观了，时间复杂度 O(n)。所以，后来才催生了以下实现。
 * 3)epoll：epoll 能直接返回具体的准备好的通道，时间复杂度 O(1)
 * </P>
 * @date 2020/6/30 3:44 下午
 */

@Slf4j
public class SelectorServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(8080));
        // 将其注册到 Selector 中，监听 OP_ACCEPT 事件
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int readyChannels = selector.select();
            log.info("select还是阻塞了其实");
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> readKeys = selector.selectedKeys();
            // 遍历
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    log.info("有新连接进来");
                    // 有已经接受的新的到服务端的连接
                    SocketChannel socketChannel = server.accept();
                    // 有新的连接并不代表这个通道就有数据，
                    // 这里将这个新的 SocketChannel 注册到 Selector，监听 OP_READ 事件，等待数据
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 有数据可读
                    log.info("有数据可读");
                    // 上面一个 if 分支中注册了监听 OP_READ 事件的 SocketChannel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int num = socketChannel.read(readBuffer);
                    if (num > 0) {
                        // 处理进来的数据...
                        System.out.println("收到数据：" + new String(readBuffer.array()).trim());
                        ByteBuffer buffer = ByteBuffer.wrap("返回给客户端的数据...".getBytes());
                        socketChannel.write(buffer);
                    } else if (num == -1) {
                        // -1 代表连接已经关闭
                        socketChannel.close();
                    }
                }
            }
        }
    }
}
