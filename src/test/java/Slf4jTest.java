import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public class Slf4jTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    public static void main(String[] args) {
        LOGGER.info("Current Time: {}", System.currentTimeMillis());
        LOGGER.info("Current Time: " + System.currentTimeMillis());
        LOGGER.info("Current Time: {}", System.currentTimeMillis());
        LOGGER.trace("trace log");
        LOGGER.warn("warn log");
        LOGGER.debug("debug log");
        LOGGER.info("info log");
        LOGGER.error("error log");
    }

    private static void test() {
        LOGGER.info("task start...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 5; i++) {
            boolean flag = false;
            try {
                flag = saveAndUpdate(i);
                if (flag) {
                    // LOGGER.info("成功执行第" + i + "全套事务");
                }
            } catch (Exception e) {
                LOGGER.error("第" + i + "错误");
                continue;
            }
        }
        stopWatch.stop();
        LOGGER.info("task end, time : {} s.", stopWatch.getTotalTimeSeconds());
    }

    private static boolean saveAndUpdate(int i) {
        boolean flag = false;
        //模拟执行事务
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i == 3) {
            int a = 1 / 0;
        }
        flag = true;
        return flag;

    }
}
