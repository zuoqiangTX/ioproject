package com.zuoqiang.test.zk.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

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

        //订阅子节点的变化
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

        zkc.createPersistent("/super" + "/" + "c1", "c1内容");
        Thread.sleep(1000);

        zkc.createPersistent("/super" + "/" + "c2", "c2内容");
        Thread.sleep(1000);

        zkc.delete("/super/c2");
        Thread.sleep(1000);

        zkc.deleteRecursive("/super");
        Thread.sleep(Integer.MAX_VALUE);

    }
}
