package com.zuoqiang.test.concurrent.job;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试job测试类
 * @date 2020/8/25 10:13 上午
 */

public class TestJobMain {
    public static void main(String[] args) {
        TestJob testJob = new TestJob();
        testJob.execute("1111");

    }
}
