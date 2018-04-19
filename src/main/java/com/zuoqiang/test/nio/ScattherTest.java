package com.zuoqiang.test.nio;

/***
 * Description:scatter / gather是通过通道读写数据的两个概念。
 *
 * 1）Scattering read指的是从通道读取的操作能把数据写入多个buffer，也就是sctters代表了数据从一个channel到多个buffer的过程。
 *
 * //scattering read内部必须写满一个buffer后才会向后移动到下一个buffer，
 * 因此这并不适合消息大小会动态改变的部分，也就是说，
 * 如果你有一个header和body，并且header有一个固定的大小（比如128字节）,这种情形下可以正常工作。
 * ByteBuffer header = ByteBuffer.allocate(128);
 * ByteBuffer body   = ByteBuffer.allocate(1024);
 * ByteBuffer[] bufferArray = { header, body };
 * channel.read(bufferArray);
 *
 *
 * 2）gathering write则正好相反，表示的是从多个buffer把数据写入到一个channel中。
 *
 * //类似的传入一个buffer数组给write，内部机会按顺序将数组内的内容写进channel，
 * 这里需要注意，写入的时候针对的是buffer中position到limit之间的数据。
 * 也就是如果buffer的容量是128字节，但它只包含了58字节数据，那么写入的时候只有58字节会真正写入。
 * 因此gathering write是可以适用于可变大小的message的，这和scattering reads不同。
 * ByteBuffer header = ByteBuffer.allocate(128);
 * ByteBuffer body   = ByteBuffer.allocate(1024);
 * //write data into buffers
 * ByteBuffer[] bufferArray = { header, body };

 channel.write(bufferArray);
 */
public class ScattherTest {
    public static void main(String[] args) {


    }
}
