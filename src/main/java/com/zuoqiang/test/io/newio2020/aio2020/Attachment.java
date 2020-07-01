package com.zuoqiang.test.io.newio2020.aio2020;

import lombok.Data;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 附件类
 * @date 2020/6/30 5:27 下午
 */

@Data
public class Attachment {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private boolean isReadMode;
    private ByteBuffer buffer;
}
