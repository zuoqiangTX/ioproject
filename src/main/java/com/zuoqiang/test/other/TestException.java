package com.zuoqiang.test.other;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试是不是会出现吞异常的情况
 * @date 2020/6/22 2:37 下午
 */

@Slf4j
public class TestException {
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(1);
            int[] a = new int[2];
//            a[3] = 5;
//            int i = 1 / 0;
            throw new RuntimeException("测试异常不捕获会不会被吞");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        log.info("经过测试,异常如果不被捕获，会向上抛出");
    }
}
