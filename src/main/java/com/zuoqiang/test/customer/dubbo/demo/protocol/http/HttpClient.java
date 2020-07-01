package com.zuoqiang.test.customer.dubbo.demo.protocol.http;

import com.zuoqiang.test.customer.dubbo.demo.protocol.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zuoqiang
 * @version 1.0
 * @description http发送端
 * @date 2020/5/13 11:17 上午
 */

public class HttpClient {
    public String send(String hostname, int port, Invocation invocation) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = connection.getInputStream();
            String result = IOUtils.toString(inputStream, "utf-8");
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
