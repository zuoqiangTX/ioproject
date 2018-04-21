package com.zuoqiang.test.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/***
 * Path路径
 * 一个Path实例代表一个文件系统内的路径。path可以指向文件也可以指向目录。可以使相对路径也可以是绝对路径。
 * @author zuoqiang
 */

public class PathsTest {
    public static void main(String[] args) {
        //创建path实例
        //绝对绝对路径
        Path path = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/nio.txt");
        System.out.println(path);

        //创建相对路径
        Path absolutePath = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources", "/data/nio.txt");
        System.out.println(absolutePath);
        absolutePath = Paths.get(".").toAbsolutePath();

        //Path的normalize()方法可以把路径规范化。也就是把.和..都等价去除：
        Path path1 = Paths.get("/Users/zuoqiang/IdeaProjects/io-project//..//./io-project/ioproject/src/main/resources");
        Path path2 = Paths.get("/Users/zuoqiang/IdeaProjects/io-project//..//./io-project/ioproject/src/main/resources").normalize();
        System.out.println("Path1 : " + path1 + "\n" + "Path2 : " + path2);


    }
}
