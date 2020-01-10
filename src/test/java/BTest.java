import org.apache.commons.lang3.StringUtils;

/**
 * 身份证后6位的处理方法
 *
 * @author zuoqiang
 */
public class BTest {
    public static void main(String[] args) {
        String aa = "1111x";
        String bb = "1111X";
        System.out.println(StringUtils.equals(aa.toLowerCase(), bb.toLowerCase()));

        String[] s = ",,".split(",");
        System.out.println("length :" + s.length);
        dealOrderExpire();
    }

    /**
     * 处理订单过期
     */
    private static void dealOrderExpire() {
        int i = 0;
        while (true) {
            i++;//相当于从mq队列中取数据，取完里面就少一个
            if (i == 100) {
                System.out.println("如果订单为null 跳出！");
                break;
            } else if (i == 2) {
                System.out.println("如果订单未超时，继续处理！");
                continue;
            }
            System.out.println("真实处理订单的逻辑" + i);
        }
    }

/**
 * redis 扣库存 先找数据库有没有记录，没有记录的话走setnx分布式锁获取缓存，
 * 没有的话走decr扣减数据，大于0继续往后走，然后数据库乐观锁保证不超
 */
//
//    @Autowired
//    private RedisComponent redisComponent;
//
//    @Test
//    public void testRedis() throws InterruptedException {
//        String x1 = "x1";
//        redisComponent.set(x1, 100, 6);
//        Thread.sleep(3000);
//        Long value = redisComponent.get(x1, Long.class);
//        if (value == null) {
//            System.out.println("数据库里没有值了哦：");
//        } else {
//            System.out.println("减完以后的数量为：" + redisComponent.incr(x1));
//        }
//
//    }
}
