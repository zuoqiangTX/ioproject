package com.zuoqiang.test.socket.nio;

import java.nio.IntBuffer;

/**
 * Buffer测试类 注意position位置
 *
 * @author zuoqiang
 */
public class TestBuffer {
    public static void main(String[] args) {

        System.out.println(">>>>>>>>>>>>方法1>>>>>>>>>>>");
        IntBuffer buffer = IntBuffer.allocate(10);
        //position位置推进0 -> 1
        buffer.put(13);
        //position位置推进1 -> 2
        System.out.println("buffer" + buffer);
        buffer.put(21);
        //position位置推进2 -> 3
        buffer.put(35);
        //把位置复为0,position位置推进3 -> 0，每次加入的时候都要把位置变成0
        buffer.flip();
        System.out.println("复位" + buffer);
        //容量初始化以后不允许改变，warp方法包裹数组除外
        System.out.println("容量" + buffer.capacity());
        //只装载了是哪个，可读取的元素为3
        System.out.println("限制" + buffer.limit());

        System.out.println("下标为1的元素：" + buffer.get(1));
        System.out.println("get(index)方法,position位置不变" + buffer);
        buffer.put(1, 4);
        System.out.println("put(index, str)方法,position位置不变" + buffer);

        for (int i = 0; i < buffer.limit(); i++) {
            //调用get以后，position向后递增一位（当前position++）
            System.out.println(buffer.get());
        }


        wrapTest();

        testMethod3();


    }

    private static void testMethod3() {
        System.out.println(">>>>>>>>>>>>方法3>>>>>>>>>>>");
        IntBuffer buffer = IntBuffer.allocate(10);
        int[] arr = new int[]{1, 2, 5};
        //put完以后，一定要flip复位
        buffer.put(arr);
        System.out.println("buffer:" + buffer);

        IntBuffer buffer1 = buffer.duplicate();
        System.out.println("一般复制方法" + buffer1);

        //设置buffer的属性
        //buffer.position(1);
        buffer.flip();
        System.out.println("buffer:" + buffer);
        //可读limit-pos
        System.out.println("可读数据" + buffer.remaining());

        int[] arr2 = new int[buffer.remaining()];
        //缓冲区数据放入数组2
        buffer.get(arr2);
        for (int i : arr2) {
            System.out.print(Integer.toString(i) + ",");
        }
    }

    private static void wrapTest() {
        System.out.println(">>>>>>>>>>>>方法2>>>>>>>>>>>");
        //不建议使用，limit为实际进入缓冲区长度，cap为包裹的数组的长度
        int[] arr = new int[]{1, 2, 5};
        //修改缓冲区，使用arr数组包裹覆盖
        IntBuffer buffer = IntBuffer.wrap(arr);
        System.out.println(buffer);

        IntBuffer buffer2 = IntBuffer.wrap(arr, 0, 2);
        System.out.println(buffer2);

    }
}
