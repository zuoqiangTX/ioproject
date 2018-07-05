package com.zuoqiang.test.socket.bio2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 测试1000情况下伪异步IO(会出现大量的超时，因为线程池拒绝连接)
 * @author zuoqiang
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static String ADDRESS = "127.0.0.1";
    private static int PORT = 8765;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    BufferedReader in = null;
                    PrintWriter out = null;
                    try {
                        socket = new Socket(ADDRESS, PORT);
                        //从服务器写入数据
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //向服务器输出数据
                        out = new PrintWriter(socket.getOutputStream(), true);

                        //1）向服务器端发送数据
                        String content = "HELLO,SERVER,I'M CLIENT" + finalI + "...";
                        out.println(content);
                        LOGGER.info("向服务器端host:{},port:{}发送数据,data:{}...", ADDRESS, PORT, content);

                        //2）从服务器端接收数据
                        String respsone = in.readLine();
                        LOGGER.info("从服务器host:{},port:{}返回数据,respsone:{}...", new Object[]{ADDRESS, PORT, respsone});

                    } catch (IOException e) {
                        LOGGER.error("host:{},port:{}出现异常,err:", ADDRESS, PORT, e);
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
            }).start();
        }
    }
}
