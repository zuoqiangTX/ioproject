import org.apache.commons.lang.StringUtils;

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
}
