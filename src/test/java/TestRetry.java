import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分页查询设置超时重试次数
 */
public class TestRetry {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRetry.class);
    /**
     * 真实重试次数为RETRYTIMES-1,RETRYTIMES时退出循环
     */
    private static final int RETRYTIMES = 5;

    /**
     * 真实环境的写法
     */
    public void TestAll() {
        int pageNo = 1;
        int totoalPageNo = 10;
        AtomicInteger retryTimes = new AtomicInteger();
        while (pageNo <= totoalPageNo) {
            try {
                LOGGER.info(String.format("第%s页任务镜像开始处理...", pageNo));
                //模拟真实业务操作
                Thread.sleep(1000);
                LOGGER.info("第{}页任务镜像[{}]处理成功", pageNo);
            } catch (Throwable e) {
                LOGGER.error("第{}页任务镜像处理时发生异常,err = ", pageNo, e);
                //重试当行
                pageNo--;
                //重试次数加1
                retryTimes.getAndIncrement();
                //LOGGER.info("第{}页重试次数{}", pageNo + 1, retryTimes.get() % 5);
                if (retryTimes.get() % RETRYTIMES == 0) {
                    int hasretryTimes = retryTimes.get();
                    //清空原有重试次数
                    retryTimes.getAndAdd(-hasretryTimes);
                    //跳过当前页数
                    pageNo = skipNowPage(pageNo);
                }
            }
            pageNo++;
        }
    }

    public static void main(String[] args) {
        int pageNo = 1;
        int totoalPageNo = 10;
        AtomicInteger retryTimes = new AtomicInteger();
        while (pageNo <= totoalPageNo) {
            try {
                //String str = retryTimes.get() % RETRYTIMES == 0 ? "第%s页任务镜像开始处理..." : "第%s页任务镜像开始处理,重试次数为%d...";
                String str = "第%s页任务镜像开始处理...";
                LOGGER.info(String.format(str, pageNo, retryTimes.get() % RETRYTIMES));
                if (pageNo == 5 || pageNo == 6) {
                    if (retryTimes.get() != 3 || retryTimes.get() != 1) {
                        throw new RuntimeException();
                    }
                }
                LOGGER.info("第{}页任务镜像[{}]处理成功", pageNo);
            } catch (Throwable e) {
                LOGGER.error("第{}页任务镜像处理时发生异常,err = ", pageNo, e);
                //重试当行
                pageNo--;
                //重试次数加1
                retryTimes.getAndIncrement();
                //LOGGER.info("第{}页重试次数{}", pageNo + 1, retryTimes.get() % 5);
                if (retryTimes.get() % RETRYTIMES == 0) {
                    int hasretryTimes = retryTimes.get();
                    //清空原有重试次数
                    retryTimes.getAndAdd(-hasretryTimes);
                    //pageNo = skipAllPage(totoalPageNo);
                    pageNo = skipNowPage(pageNo);
                }
            }
            pageNo++;
        }
    }

    /**
     * 重试数次无果时，退出整个循环
     *
     * @param totoalPageNo
     * @return
     */
    private static int skipAllPage(int totoalPageNo) {
        int pageNo;
        pageNo = totoalPageNo + 1;
        return pageNo;
    }

    /**
     * 重试数行后，跳过当前页数
     *
     * @param pageNo
     * @return
     */
    private static int skipNowPage(int pageNo) {
        pageNo++;
        return pageNo;
    }


}
