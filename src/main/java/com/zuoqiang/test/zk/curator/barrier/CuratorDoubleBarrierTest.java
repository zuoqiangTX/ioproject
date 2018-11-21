package com.zuoqiang.test.zk.curator.barrier;

import com.zuoqiang.test.zk.curator.integer.CuratorIntegerTest;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 分布式双栅栏(同时开始同时结束类似)
 *
 * @author baiyue
 * @desc 双栅栏允许客户端在计算的开始和结束时同步。
 * 当足够的进程加入到双栅栏时，进程开始计算， 当计算完成时，离开栅栏。
 * 双栅栏类是DistributedDoubleBarrier。
 */
public class CuratorDoubleBarrierTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorIntegerTest.class);
    /**
     * zookeeper地址
     */
    private static final String CONNECT_ADDR = "192.168.85.2:2181";
    /**
     * session超时时间//ms
     */
    private static final int SESSION_OUTTIME = 5000;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
                        CuratorFramework cf = CuratorFrameworkFactory.builder()
                                .connectString(CONNECT_ADDR)
                                .retryPolicy(retryPolicy)
                                .build();
                        cf.start();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cf, "/super", 5);
                        Thread.sleep(1000 * (new Random()).nextInt(3));
                        System.out.println(Thread.currentThread().getName() + "已经准备");
                        barrier.enter();
                        System.out.println("同时开始运行...");
                        Thread.sleep(1000 * (new Random()).nextInt(3));
                        System.out.println(Thread.currentThread().getName() + "运行完毕");
                        barrier.leave();
                        System.out.println("同时退出运行...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "t" + i).start();
        }
    }
}
