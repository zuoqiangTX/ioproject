package com.zuoqiang.test.arithmetic.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 平滑权重
 * @date 2021/2/24 4:06 下午
 */

public class WeightRoundRobinV2 {
    private static Map<String, Weight> weightMap = new HashMap<String, Weight>();
    public static String getServer() {
        // java8
        int totalWeight = ServerIps.WEIGHT_LIST.values().stream().reduce(0, (w1, w2) -> w1+w2);
        // 初始化weightMap，初始时将currentWeight赋值为weight
        if (weightMap.isEmpty()) {
            ServerIps.WEIGHT_LIST.forEach((key, value) -> {
                weightMap.put(key, new Weight(key, value, value));
            });
        }
        // 找出currentWeight最大值
        Weight maxCurrentWeight = null;
        for (Weight weight : weightMap.values()) {
            if (maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()) {
                maxCurrentWeight = weight;
            }
        }
        // 将maxCurrentWeight减去总权重和
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);
        // 所有的ip的currentWeight统一加上原始权重
        for (Weight weight : weightMap.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
        }
        // 返回maxCurrentWeight所对应的ip
        return maxCurrentWeight.getIp();
    }
    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
