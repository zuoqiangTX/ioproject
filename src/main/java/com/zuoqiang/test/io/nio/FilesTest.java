package com.zuoqiang.test.io.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/***
 * NIO Files
 * java.nio.file.Files类是和java.nio.file.Path相结合使用的，所以在用Files之前确保你已经理解了Path类。
 * @author zuoqiang
 */

public class FilesTest {
    public static void main(String[] args) throws IOException {
        //File.exists()检查这个路径是否真实存在    LinkOptions.NOFOLLOW_LINKS，表示检测时不包含符号链接文件
        Path path = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/nio.txt");
        boolean exists = Files.exists(path, new LinkOption[]{
                LinkOption.NOFOLLOW_LINKS
        });
        System.out.println("该路径真实存在吗？" + exists);

        //创建Path路径,删除Path路径
        Path creatPath = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/test");
        try {
            Files.createDirectory(creatPath);
            TimeUnit.SECONDS.sleep(60);
        } catch (FileAlreadyExistsException e) {
            System.out.println("该路径已经存在");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Files.delete(creatPath);


    }
}
