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

    }
}
