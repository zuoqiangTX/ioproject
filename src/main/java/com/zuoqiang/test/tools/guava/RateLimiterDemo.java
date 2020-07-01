package com.zuoqiang.test.tools.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 　google开源工具包guava提供了限流工具类RateLimiter，该类基于“令牌桶算法”，非常方便使用。
 * @date 2020/5/8 11:01 上午
 */

public class RateLimiterDemo {
    public static void main(String[] args) {
        testNoRateLimiter();
        testWithRateLimiter();
    }

    private static void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    private static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
