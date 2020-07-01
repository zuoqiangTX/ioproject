package com.zuoqiang.test.io.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * nio2.0 异步非阻塞
 *
 * @author zuoqiang
 */
public class Client implements Runnable{
    private AsynchronousSocketChannel asc;

    public Client() throws IOException {
        this.asc = AsynchronousSocketChannel.open();
    }

    public void connect() {
        asc.connect(new InetSocketAddress("127.0.0.1", 8765));
    }

    public void write(String request) {
        try {
            //异步去写
            asc.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //异步去读
            asc.read(buffer).get();
            buffer.flip();
            byte[] respByte = new byte[buffer.remaining()];
            buffer.get(respByte);
            System.out.println(new String(respByte, "utf-8").trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Client c1 = new Client();
        c1.connect();

        Client c3 = new Client();
        c3.connect();

        Client c2 = new Client();
        c2.connect();

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

        Thread.sleep(1000);
        c1.write("c1 aaa");
        c2.write("c2 bbb");
        c3.write("c3 ccc");
    }

    @Override
    public void run() {
        while (true) {
        }
    }
}
