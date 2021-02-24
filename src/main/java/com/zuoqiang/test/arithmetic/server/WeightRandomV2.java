package com.zuoqiang.test.arithmetic.server;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 权重轮询v2
 * <p>
 * 假设我们有一组服务器 servers = [A, B, C]，他们对应的权重为 weights = [5, 3, 2]，权重总和为10。
 * 现在把这些权重值平铺在一维坐标值上，[0, 5) 区间属于服务器 A，[5, 8) 区间属于服务器 B，[8, 10) 区间属于服务器 C。
 * 接下来通过随机数生成器生成一个范围在 [0, 10) 之间的随机数，然后计算这个随机数会落到哪个区间上。比如数字3会落到服务器 A 对应的区间上
 * ，此时返回服务器 A 即可。权重越大的机器，在坐标轴上对应的区间范围就越大，因此随机数生成器生成的数字就会有更大的概率落到此区间内。
 * 只要随机数生成器产生的随机数分布性很好，在经过多次选择后，每个服务器被选中的次数比例接近其权重比例。比如，经过一万次选择后，
 * 服务器 A 被选中的次数大约为5000次，服务器 B 被选中的次数约为3000次，服务器 C 被选中的次数约为2000次。
 * </P>
 * @date 2021/2/24 3:52 下午
 */

public class WeightRandomV2 {
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
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(totalWeight);
        if (!sameWeight) {
            for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
                Integer value = ServerIps.WEIGHT_LIST.get(ip);
                if (randomPos < value) {
                    return ip;
                }
                randomPos = randomPos - value;
            }
        }
        return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[new java.util.Random().nextInt(ServerIps.WEIGHT_LIST.size())];
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
