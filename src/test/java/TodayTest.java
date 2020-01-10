import org.apache.commons.lang3.StringUtils;

public class TodayTest {
    public static void main(String[] args) {
        String passName = "";
        String passPhone = "";

        //加密用户名称
        String userName = "王淼淼";
        if (StringUtils.isNotBlank(userName)) {
            int length = userName.length();
            String str = "";
            for (int i = 0; i < userName.length() - 1; i++) {
                str += "*";
            }
            passName = new StringBuffer(userName).replace(1, length, str).toString();
        }
        String phone = "15958190301";
        //加密手机号
        if (StringUtils.isNotBlank(phone) && phone.length() > 7) {
            passPhone = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        }

        System.out.println(passName);
        System.out.println(passPhone);
    }
}