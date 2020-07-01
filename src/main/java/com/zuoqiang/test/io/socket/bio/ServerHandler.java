package com.zuoqiang.test.io.socket.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * SocketIO 服务器端处理逻辑
 *
 * @author zuoqiang
 */
public class ServerHandler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            //输入
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            //输出,此处不自动刷新
            out = new PrintWriter(this.socket.getOutputStream());
            String body;
            while (true) {
                //每次读取一行
                body = in.readLine();
                if (body == null) {
                    break;
                }
                LOGGER.info("接收到客户端的数据,data:{}...", body);
                out.println("HELLO,CLIENT...");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
