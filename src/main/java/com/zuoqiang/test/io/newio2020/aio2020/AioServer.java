package com.zuoqiang.test.io.newio2020.aio2020;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 异步io服务端
 * <p>
 * Java 中的异步 IO 也是一样的，都是由一个线程池来负责执行任务，然后使用回调或自己去查询结果。
 * 异步 IO 主要是为了控制线程数量，减少过多的线程带来的内存消耗和 CPU 在线程调度上的开销。
 * </p>
 * <p>
 * 总共有三个类需要我们关注，分别是 AsynchronousSocketChannel，AsynchronousServerSocketChannel
 * 和 AsynchronousFileChannel，只不过是在之前介绍的 FileChannel、SocketChannel 和 ServerSocketChannel
 * 的类名上加了个前缀 Asynchronous。
 * Java 异步 IO 提供了两种使用方式，分别是返回 Future 实例和使用回调函数。
 * </P>
 * @date 2020/6/30 5:25 下午
 */

public class AioServer {
    public static void main(String[] args) throws IOException {
        // 实例化，并监听端口
        AsynchronousServerSocketChannel server =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));
        // 自己定义一个 Attachment 类，用于传递一些信息
        Attachment att = new Attachment();
        att.setServer(server);
        server.accept(att, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Attachment attachment) {
                SocketAddress clientAddr = null;
                try {
                    clientAddr = client.getRemoteAddress();
                    System.out.println("收到新的连接：" + clientAddr);
                    // 收到新的连接后，server 应该重新调用 accept 方法等待新的连接进来
                    att.getServer().accept(att, this);

                    Attachment newAtt = new Attachment();
                    newAtt.setServer(server);
                    newAtt.setClient(client);
                    newAtt.setReadMode(true);
                    newAtt.setBuffer(ByteBuffer.allocate(2048));

                    // 这里也可以继续使用匿名实现类，不过代码不好看，所以这里专门定义一个类
                    client.read(newAtt.getBuffer(), newAtt, new ChannelHandler());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Attachment attachment) {
                System.out.println("accept failed");
            }
        });
        // 为了防止 main 线程退出
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }

    }
}
