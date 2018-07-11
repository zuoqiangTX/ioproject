import com.tongbanjie.commons.util.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilTest {
    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
        TimeUnit.SECONDS.sleep(10);
        Date date2 = new Date();
        System.out.println(DateUtil.getDiffSeconds(date2, date1));
    }

}
