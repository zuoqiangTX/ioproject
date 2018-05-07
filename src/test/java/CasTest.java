import java.util.concurrent.atomic.AtomicBoolean;

public class CasTest {
    public static void main(String[] args) {
        AtomicBoolean isStarted = new AtomicBoolean(false);

        System.out.println("第一个线程进来，任务还没执行的时候: " + !isStarted.compareAndSet(false, true));
        System.out.println("任务执行起来了，此时isStarted = " + isStarted);
        System.out.println("第二个线程进来，任务已经启动了: " + !isStarted.compareAndSet(false, true));
        System.out.println("任务执行起来了，此时isStarted = " + isStarted);
        System.out.println("第三个线程进来，任务已经启动了: " + !isStarted.compareAndSet(false, true));

    }

    private class Job {
        private AtomicBoolean flag = new AtomicBoolean(false);

        public String execute() {
            if (!flag.compareAndSet(false, true)) {
                System.out.println("任务已经执行！即将退出");
                return "";
            }
            //任务的具体逻辑
            return "";
        }
    }
}
