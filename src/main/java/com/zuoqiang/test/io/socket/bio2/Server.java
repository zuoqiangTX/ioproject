package com.zuoqiang.test.io.socket.bio2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketIO 服务端（伪异步IO,采用线程池和队列解决问题）
 *
 * @author zuoqiang
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private static final int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            server = new ServerSocket(PORT);
            Socket socket = null;
            LOGGER.info("服务器开始...");
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
            while (true) {
                socket = server.accept();
                //将接收到的socket放入线程池中
                executorPool.execute(new ServerHandler(socket));
            }
        } catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
