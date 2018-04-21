package com.zuoqiang.test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/***
 * AsynchronousFileChannel异步文件通道
 *
 */

public class AsFileChannelTest {
    public static void main(String[] args) throws IOException {
        //1 create channel
        Path path = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/nio.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        Path writePath = Paths.get("/Users/zuoqiang/IdeaProjects/io-project/ioproject/src/main/resources/data/FileNIO.txt");
        if (!Files.exists(writePath)) {
            System.out.println("文件不存在，准备创建！");
            Files.createFile(writePath);
        }
        System.out.println("文件已存在！开始写入");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(writePath, StandardOpenOption.WRITE);

        //读的方式两种
        //byFutureRead(fileChannel);
        //byCompletionHandlerRead(fileChannel);

        //写的方式也有两种
        //byFutureWrite(asynchronousFileChannel);
        byCompletionHandlerWrite(asynchronousFileChannel);
    }

    private static void byFutureWrite(AsynchronousFileChannel fileChannel) {
        //TODO 写入会乱码。。。
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put(("This is a test by Future").getBytes());
        System.out.println("现在的positon is " + buffer.position() + ",limit is " + buffer.limit());
        buffer.flip();
        //写模式 --> 读模式 调用flip方法会把position归零，并设置limit为之前的position的值。

        Future<Integer> operiton = fileChannel.write(buffer, position);
        buffer.clear();
        while (!operiton.isDone()) {
            //System.out.println("check !");
        }
        System.out.println("完成写入！");

    }

    private static void byCompletionHandlerWrite(AsynchronousFileChannel fileChannel) {
        //TODO 无法执行，不知道什么问题
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("test data".getBytes());
        buffer.flip();
        //当数据写入完成后completed()会被调用，如果失败了那么failed()会被调用。
        fileChannel.write(buffer, position, null, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("failed!");
                exc.printStackTrace();
            }
        });

    }

    public static void byFutureRead(AsynchronousFileChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(48);
        //第二个参数是开始读取数据的位置。
        long position = 0;
        //read()方法会立刻返回，即使读操作没有完成。我们可以通过isDone()方法检查操作是否完成。
        Future<Integer> readChannel = channel.read(buffer, 0);
        while (!readChannel.isDone()) {
            //等待读取完毕，读入Buffer中;
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.print(new String(data));
        buffer.clear();

    }

    public static void byCompletionHandlerRead(AsynchronousFileChannel channel) {
        //TODO 无法执行，不知道什么问题
        ByteBuffer buffer = ByteBuffer.allocate(48);
        long position = 0;
        //一旦读取完成，将会触发CompletionHandler的completed()方法
        //前面的整形表示的是读取到的字节数大小。第二个ByteBuffer也可以换成其他合适的对象方便数据写入。
        channel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result is : " + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
