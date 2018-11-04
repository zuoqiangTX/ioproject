package com.zuoqiang.test.zk.zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.checkerframework.checker.units.qual.K;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZkTest {

    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "127.0.0.1:2181";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms


    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(CONNECT_ADDR, SESSION_OUTTIME);

//        //创建节点
//        zkClient.createEphemeral("/temp");
//        //value 必须存在父节点，才能设置
//        zkClient.createPersistent("/super/temp","11");
//        //递归创建节点
//        zkClient.createPersistent("/test/sss",true);
//

//        //删除节点
//        zkClient.delete("/test");  //非递归删除
//        zkClient.deleteRecursive("/test");  //递归删除


        //2. 设置path和data 并且读取子节点和每个节点的内容
 /*       zkClient.createPersistent("/super", "1234");
        zkClient.createPersistent("/super/c1", "c1内容");
        zkClient.createPersistent("/super/c2", "c2内容");
        List<String> zkList = zkClient.getChildren("/super");
        for (String zk : zkList) {
            System.out.println(zk);
            System.out.println(zk);
			String rp = "/super/" + zk;
			String data = zkClient.readData(rp);
			System.out.println("节点为：" + rp + "，内容为: " + data);
        }*/


        //3. 更新和判断节点是否存在
		zkClient.writeData("/super/c1", "新内容");
		System.out.println(zkClient.readData("/super/c1"));
		System.out.println(zkClient.exists("/super/c1"));

        //4.递归删除/super内容
//        zkClient.deleteRecursive("/super");



        TimeUnit.SECONDS.sleep(10);

        zkClient.close();
    }
}
