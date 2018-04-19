import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(" begin to do something at:" + sdf.format(new Date()));
        //schedule.scheduleWithFixedDelay(new Job("zuoqiang"), 0, 2, TimeUnit.SECONDS);
        schedule.scheduleWithFixedDelay(new Job("JiangKe"), 0, 4, TimeUnit.SECONDS);  //每4秒执行一次
        schedule.schedule(new Task(), 1, TimeUnit.SECONDS);  //1秒后执行一次
    }

    static class Job implements Runnable {
        private String name;

        public Job(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("say something " + name + new Date());
        }
    }

    static class Task implements Callable {
        public Object call() throws Exception {
            int x = new Random(5).nextInt();
            System.out.println(x);
            return x;
        }
    }

    }

