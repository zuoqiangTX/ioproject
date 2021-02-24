package com.zuoqiang.test.arithmetic.server;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 随机算法-RandomLoadBalance【负载均衡算法】
 * <p>
 * 当调用次数比较少时，Random 产生的随机数可能会比较集中，此时多数请求会落到同一台服务器上，只有在经过多次请求后，才能使调用请求进行“均匀”分配。调用量少这一点并没有什么关系，负载均衡机制不正是为了应对请求量多的情况吗，所以随机算法也是用得比较多的一种算法。
 * <p>
 * 但是，上面的随机算法适用于每天机器的性能差不多的时候，实际上，生产中可能某些机器的性能更高一点，它可以处理更多的请求，所以，我们可以对每台服务器设置一个权重。
 * <p>
 * 在ServerIps类中增加服务器权重对应关系MAP，权重之和为50：
 * </p>
 * @date 2021/2/24 3:42 下午
 */

public class SimpleRandom {

    public static String getServer() {
        // 生成一个随机数作为list的下标值
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(ServerIps.LIST.size());
        return ServerIps.LIST.get(randomPos);
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
