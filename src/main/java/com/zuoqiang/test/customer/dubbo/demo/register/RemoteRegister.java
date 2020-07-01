package com.zuoqiang.test.customer.dubbo.demo.register;

import com.zuoqiang.test.customer.dubbo.demo.framework.URL;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 模拟注册中心
 * @date 2020/5/13 10:11 上午
 */

public class RemoteRegister {
    private static Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();

    public static void register(String interfaceName, URL url) throws IOException {
        List<URL> urls = Collections.singletonList(url);
        REGISTER.put(interfaceName, urls);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        return getFile().get(interfaceName);
    }

    private static void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("temp.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fis = new FileInputStream("temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, List<URL>> map = (Map<String, List<URL>>) ois.readObject();
            ois.close();
            fis.close();
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 负载均衡算法，这里采用随机算法
     *
     * @param interfaceName
     * @return
     */
    public static URL getRandom(String interfaceName) {
        List<URL> urls = RemoteRegister.get(interfaceName);
        int i = new Random().nextInt(urls.size());
        return urls.get(i);
    }
}
