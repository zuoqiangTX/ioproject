package com.zuoqiang.test.zk.curator.integer;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分布式计数器
 *
 * @author baiyue
 */
public class CuratorIntegerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorIntegerTest.class);
    /**
     * zookeeper地址
     */
    private static final String CONNECT_ADDR = "192.168.85.2:2181";
    /**
     * session超时时间//ms
     */
    private static final int SESSION_OUTTIME = 5000;

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client,
                "/super", new RetryNTimes(3, 1000));
        atomicInteger.forceSet(0);
        //执行increment操作
        AtomicValue<Integer> value = atomicInteger.increment();
        System.out.println(value.succeeded());
        //操作前的值
        System.out.println(value.preValue());
        //操作后的值
        System.out.println(value.postValue());
    }
}
