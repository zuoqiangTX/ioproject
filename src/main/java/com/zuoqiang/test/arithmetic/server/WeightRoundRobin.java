package com.zuoqiang.test.arithmetic.server;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 轮询权重
 * @date 2021/2/24 3:59 下午
 */

public class WeightRoundRobin {
    private static Integer pos = 0;

    public static String getServer() {
        int totalWeight = 0;
        boolean sameWeight = true; // 如果所有权重都相等，那么随机一个ip就好了
        Object[] weights = ServerIps.WEIGHT_LIST.values().toArray();
        for (int i = 0; i < weights.length; i++) {
            Integer weight = (Integer) weights[i];
            totalWeight += weight;
            if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                sameWeight = false;
            }
        }
        Integer sequenceNum = Sequence.getAndIncrement();
        Integer offset = sequenceNum % totalWeight;
        offset = offset == 0 ? totalWeight : offset;
        if (!sameWeight) {
            for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
                Integer weight = ServerIps.WEIGHT_LIST.get(ip);
                if (offset <= weight) {
                    return ip;
                }
                offset = offset - weight;
            }
        }
        String ip = null;
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
        // 连续调用11次
        for (int i = 0; i < 50; i++) {
            System.out.println(getServer());
        }
    }
}
