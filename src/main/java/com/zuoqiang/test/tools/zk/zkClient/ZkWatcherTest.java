package com.zuoqiang.test.tools.zk.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 不需要自己实现（原生API）重复注册watch的机制呃,使用方便了很多
 *
 * @author zuoqiang
 */
public class ZkWatcherTest {
    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "127.0.0.1:2181";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkc = new ZkClient(CONNECT_ADDR, SESSION_OUTTIME);

        //订阅当前节点的子节点的变化新增或者删除，不监听子节点数据的变化
        zkc.subscribeChildChanges("/super", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("parentPath: " + parentPath);
                System.out.println("currentChilds: " + currentChilds);

            }
        });
        Thread.sleep(3000);

        zkc.createPersistent("/super");
        Thread.sleep(1000);

        System.out.println("-------------");
        //只监听节点的变化，不监听数据的变化
        zkc.writeData("/super", "sssss");
        Thread.sleep(1000);
        System.out.println("-------------");


        zkc.createPersistent("/super" + "/" + "c1", "c1内容");
        Thread.sleep(1000);

        zkc.createPersistent("/super" + "/" + "c2", "c2内容");
        Thread.sleep(1000);

        System.out.println("-------------");
        //并不会监听子节点的update操作
        zkc.writeData("/super" + "/" + "c1", "xxxxx");
        Thread.sleep(1000);
        System.out.println("-------------");

        zkc.delete("/super/c2");
        Thread.sleep(1000);

        zkc.deleteRecursive("/super");
        Thread.sleep(Integer.MAX_VALUE);

    }
}
