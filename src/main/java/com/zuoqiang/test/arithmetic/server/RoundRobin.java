package com.zuoqiang.test.arithmetic.server;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 轮询算法-RoundRobinLoadBalance
 * <p>
 * 这种算法很简单，也很公平，每台服务轮流来进行服务，但是有的机器性能好，
 * 所以能者多劳，和随机算法一下，加上权重这个维度之后，
 * 其中一种实现方法就是复制法，这里就不演示了，这种复制算法的缺点和随机算法的是一样的，
 * </p>
 * @date 2021/2/24 3:56 下午
 */

public class RoundRobin {
    // 当前循环的位置
    private static Integer pos = 0;

    public static String getServer() {
        String ip = null;
        // pos同步
        synchronized (pos) {
            if (pos >= ServerIps.LIST.size()) {
                pos = 0;
            }
            ip = ServerIps.LIST.get(pos);
            pos++;
        }
        return ip;
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 11; i++) {
            System.out.println(getServer());
        }
    }
}
