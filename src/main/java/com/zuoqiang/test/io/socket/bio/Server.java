package com.zuoqiang.test.io.socket.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketIO 服务器端
 *
 * @author zuoqiang
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private static final int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            LOGGER.info(">>>>>>>>>>>>服务器在{}端口上启动>>>>>>>>>>>>", PORT);
            //进行阻塞，直到收到socket的套接字
            Socket socket = server.accept();
            //新建一个线程处理客户端的应用
            new Thread(new ServerHandler(socket)).start();
        } catch (IOException e) {
            LOGGER.error("服务器处理出现异常,port:{},err:", PORT, e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    LOGGER.error("服务器关闭出现异常,port:{},err:", PORT, e);
                } finally {
                    server = null;
                }
            }
        }
    }
}
