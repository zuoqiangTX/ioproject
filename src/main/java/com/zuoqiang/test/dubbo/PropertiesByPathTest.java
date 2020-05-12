package com.zuoqiang.test.dubbo;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/12 4:51 下午
 * <p>
 * Properties属性文件是软件常用的配置文件的格式，也因为其简洁和便利的特性，
 * 经常作为小规模的数据文件 ( 其相对于普通文本，具有一定的结构性，相对于结构性强的XML又很简洁 )。
 * <p>
 * 其每一行的格式为 "key=value" , 有若干行！JAVA为这种属性文件专门提供了一个类 java.util.Properties ,
 * 我们通过这个类，可以非常容易的获取 Properties 文件中的信息。
 * </P>
 */

@Slf4j
public class PropertiesByPathTest {
    //
    public static void main(String[] args) {
        try {
            /**
             * 1、从目标路径test.properites中获取输入流对象
             *
             * 2、使用Properties类的load()方法从字节输入流中获取数据
             *
             * 3、直接打印Properties对象
             *
             * 4、使用Properties类的getProperty(String key)方法，根据参数key获取value
             */
            Properties properties = new Properties();
            InputStream in = PropertiesByPathTest.class.getClassLoader().getResourceAsStream(
                    "test.properties");
            properties.load(in);
            //直接输出prop对象
            System.out.println("直接输出prop对象:" + properties);
            //获取name的值
            String name = properties.getProperty("name");
            //获取address的值
            String address = properties.getProperty("address");
            //获取job的值
            String job = properties.getProperty("job");
            System.out.println("name=" + name + ",address=" + address + ",job=" + job);
            //job的值是乱码，说明在配置文件中不可以直接使用中文。还有#号后面的注释没有打印出来。
        } catch (Exception e) {
            log.error("加载propeties文件失败,err :", e);
        }

    }
}
