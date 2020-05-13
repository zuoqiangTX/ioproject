package com.zuoqiang.test.dubbo.ext;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Properties
 * @date 2020/5/12 4:51 下午
 * <p>
 * Properties 文件很简单，就是一行一行的 "key=value" 这种数据。
 * <p>
 * 文件后缀为 properties (也可以为其他，这个无所谓)，文件格式为普通文本。
 * </P>
 */

@Slf4j
public class PropertiesByUrlTest {
    //
    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            Properties prop2 = new Properties();
//             String path =Test.class.getClassLoader().getResource("example/china/test2.properties").getPath();
            //获得URL路径
            URL url = PropertiesByUrlTest.class.getClassLoader().getResource("data/test2.properties");
            //打印路径
            System.out.println("url.getFile()=" + url.getFile());
            //将路径中的中文转码
            String path = URLDecoder.decode(url.getFile(), "utf-8");
            System.out.println("path=" + path);
            //通过路径获得字节输入流
            InputStream input = new FileInputStream(path);
            //直接获得字节输入流
            InputStream in = PropertiesByUrlTest.class.getClassLoader().getResourceAsStream("data/test2.properties");
            prop.load(in);
            prop2.load(input);
            System.out.println("prop=" + prop);
            System.out.println("prop2=" + prop2);
        } catch (Exception e) {
            log.error("加载propeties文件失败,err :", e);
        }

    }
}
