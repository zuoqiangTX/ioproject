package com.zuoqiang.test.arithmetic.server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 权重随机算法
 * <p>
 * 简单的实现思路是，把每个服务器按它所对应的服务器进行复制
 * 这种实现方法在遇到权重之和特别大的时候就会比较消耗内存，因为需要对ip地址进行复制，权重之和越大那么上文中的ips就需要越多的内存.
 * </P>
 * @date 2021/2/24 3:49 下午
 */

public class WeightRandom {
    public static String getServer() {
        // 生成一个随机数作为list的下标值
        List<String> ips = new ArrayList<String>();
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            // 按权重进行复制
            for (int i = 0; i < weight; i++) {
                ips.add(ip);
            }
        }
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(ips.size());
        return ips.get(randomPos);
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
