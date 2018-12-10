package thread;

import java.util.concurrent.TimeUnit;

/**
 * 断点上直接右键设置 把 All 改为 Thread。
 * <p>
 * 我们可以找到指定的断点，对当前断点进行设置，如果是All，则是在主线程上进行断点，
 * 而选择Thread，就可以在当前线程进行断点；
 * </p>
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "-------------进入");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(currentThread.getName() + "-------------离开");
        }

    }
}