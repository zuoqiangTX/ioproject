/**
 * 银行卡加密测试
 *
 * @author baiyue
 */
public class ReoplaceTest {
    public static void main(String[] args) {
        String bankCard1 = "1234123444566664";
        String bankCard2 = "12341234445666645";
        StringBuffer buffer1 = new StringBuffer(bankCard1);
        StringBuffer buffer2 = new StringBuffer(bankCard2);
        //加密中间12位
        System.out.println(buffer1.replace(4, 12, "********").toString());
        System.out.println(buffer2.replace(4, 12, "********").toString());

        //加密后4位
        StringBuffer encryData1 = new StringBuffer("");
        for (int i = 0; i < buffer1.length() - 8; i++) {
            encryData1.append("*");
        }
        StringBuffer encryData2 = new StringBuffer("");
        for (int i = 0; i < buffer2.length() - 8; i++) {
            encryData2.append("*");
        }
        System.out.println(buffer1.replace(4, buffer1.length() - 4, encryData1.toString()).toString());
        System.out.println(buffer2.replace(4, buffer2.length() - 4, encryData2.toString()).toString());

    }
}
