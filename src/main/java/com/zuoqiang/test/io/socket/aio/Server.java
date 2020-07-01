package com.zuoqiang.test.io.socket.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio2.0 异步非阻塞
 *
 * @author zuoqiang
 */
public class Server {
    public ExecutorService executorService;
    public AsynchronousChannelGroup threadGroup;
    public AsynchronousServerSocketChannel assc;

    public Server(int port) {
        try {
            //创建缓冲线程池
            this.executorService = Executors.newCachedThreadPool();
            //创建线程组
            this.threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            //创建服务器通道
            this.assc = AsynchronousServerSocketChannel.open(threadGroup);
            //绑定端口号
            assc.bind(new InetSocketAddress(port));

            System.out.println("server start, port:" + port);
            //进行阻塞（但是不是一直阻塞的那种）
            assc.accept(this, new ServerCompletionHandler());
            //一直阻塞，不让服务器停止
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8765);
    }
}
