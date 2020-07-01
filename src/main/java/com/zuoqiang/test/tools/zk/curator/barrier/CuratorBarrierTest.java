package com.zuoqiang.test.tools.zk.curator.barrier;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 分布式单栅栏
 *
 * @author baiyue
 * @desc <p>
 * 分布式Barrier是这样一个类： 它会阻塞所有节点上的等待进程，直到某一个被满足， 然后所有的节点继续进行。
 * 比如赛马比赛中， 等赛马陆续来到起跑线前。 一声令下，所有的赛马都飞奔而出。
 * 当栅栏同时释放的时候，都从等待的地方执行
 * </p>
 */
public class CuratorBarrierTest {
    /**
     * zookeeper地址
     */
    private static final String CONNECT_ADDR = "192.168.85.2:2181";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    static DistributedBarrier barrier = null;

    public static void main(String[] args) throws Exception {

        /**
         * controlBarrier来设置栅栏和移除栅栏。
         * 我们创建了5个线程，在此Barrier上等待。 最后移除栅栏后所有的线程才继续执行。
         * 如果你开始不设置栅栏，所有的线程就不会阻塞住。
         */
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
                        CuratorFramework cf = CuratorFrameworkFactory.builder()
                                .connectString(CONNECT_ADDR)
                                .sessionTimeoutMs(SESSION_OUTTIME)
                                .retryPolicy(retryPolicy)
                                .build();
                        cf.start();
                        barrier = new DistributedBarrier(cf, "/super");
                        System.out.println(Thread.currentThread().getName() + "设置barrier!");
                        barrier.setBarrier();    //设置
                        barrier.waitOnBarrier();    //等待
                        System.out.println("---------开始执行程序----------");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "t" + i).start();
        }

        Thread.sleep(5000);
        barrier.removeBarrier();    //释放栅栏，在await的方法就继续往下执行


    }
}
