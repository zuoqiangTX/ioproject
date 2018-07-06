import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 当线程池的coreThread数量为2的时候，两个任务互相不影响，还是可以的。
 * 当线程池的coreThread数量为1的时候，一个任务会有可能一直占据，影响。
 * scheduleAtFixedRate 它的间隔时间是上个任务全部执行完之后+period,不会出现一个任务多个线程的情况。
 */
public class ScheduleTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(2);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s执行一次" + System.currentTimeMillis());
            }
        }, 0,20,TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("10s执行一次" + System.currentTimeMillis());
                try {
                    System.out.println("任务执行60s");
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0,10,TimeUnit.SECONDS);
    }
}
