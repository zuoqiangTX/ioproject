package com.zuoqiang.test.arithmetic.server;

import java.util.*;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 最小活跃算法
 * <p>
 * 在服务运行一段时间后，性能好的服务提供者处理请求的速度更快，因此活跃数下降的也越快，
 * 此时这样的服务提供者能够优先获取到新的服务请求、这就是最小活跃数负载均衡算法的基本思想。
 * </P>
 * @date 2021/2/24 4:11 下午
 */

public class LeastActive {

    private static String getServer() {
        // 找出当前活跃数最小的服务器
        Optional<Integer> minValue = ServerIps.ACTIVITY_LIST.values().stream().min(Comparator.naturalOrder());
        if (minValue.isPresent()) {
            List<String> minActivityIps = new ArrayList<>();
            ServerIps.ACTIVITY_LIST.forEach((ip, activity) -> {
                if (activity.equals(minValue.get())) {
                    minActivityIps.add(ip);
                }
            });
            // 最小活跃数的ip有多个，则根据权重来选，权重大的优先
            if (minActivityIps.size() > 1) {
                // 过滤出对应的ip和权重
                Map<String, Integer> weightList = new LinkedHashMap<String, Integer>();
                ServerIps.WEIGHT_LIST.forEach((ip, weight) -> {
                    if (minActivityIps.contains(ip)) {
                        weightList.put(ip, ServerIps.WEIGHT_LIST.get(ip));
                    }
                });
                int totalWeight = 0;
                boolean sameWeight = true; // 如果所有权重都相等，那么随机一个ip就好了
                Object[] weights = weightList.values().toArray();
                for (int i = 0; i < weights.length; i++) {
                    Integer weight = (Integer) weights[i];
                    totalWeight += weight;
                    if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                        sameWeight = false;
                    }
                }
                java.util.Random random = new java.util.Random();
                int randomPos = random.nextInt(totalWeight);
                if (!sameWeight) {
                    for (String ip : weightList.keySet()) {
                        Integer value = weightList.get(ip);
                        if (randomPos < value) {
                            return ip;
                        }
                        randomPos = randomPos - value;
                    }
                }
                return (String) weightList.keySet().toArray()[new java.util.Random().nextInt(weightList.size())];
            } else {
                return minActivityIps.get(0);
            }
        } else {
            return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[new java.util.Random().nextInt(ServerIps.WEIGHT_LIST.size())];
        }
    }

    public static void main(String[] args) {
        // 连续调用10次,随机10个client
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
