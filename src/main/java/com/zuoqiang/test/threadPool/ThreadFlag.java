package com.zuoqiang.test.threadPool;

import java.util.concurrent.TimeUnit;

/**
 * 那么我们应该如何停止一个线程呢？我们可以使用"已请求取消"标志的方法停止线程。
 * 我们先在任务中定义该标志，然后任务会定期的查看该标志，如果设置了这个标志，那么任务将提前结束。
 * 注意，为保证这个过程能可靠地工作，标志必须设置为volatile类型：
 * <p>
 * java中止线程的执行
 * 1）sleep wait io阻塞的情况下，对于这种线程退出的可以采用这种方法。主要方法原理就是使用interrupt方法设置中断状态，然后在catch代码块中执行如下逻辑。
 * 2)循环执行体设置执行标志位
 */
public class ThreadFlag {

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator t1 = new PrimeGenerator();
        Thread t = new Thread(t1, "thread1");
        t.start();
        TimeUnit.SECONDS.sleep(5);
        t1.cancel();
        System.out.println("检查线程的运行状态标志flag:" + !t1.cancelled);
        System.out.println(t.getName() + " is interrupt: " + t.isInterrupted());

    }

    public static class PrimeGenerator implements Runnable {
        private volatile boolean cancelled;
        private int i = 0;

        @Override
        public void run() {
            System.out.println("thread运行状态:" + !cancelled);
            while (!cancelled) {
                synchronized (this) {
                    //你需要用synchronized 去控制线程的交互。把线程共享的变量放到synchronized 块里面
                    i++;
                }
            }
            System.out.println("输出结果:  i= " + i);

        }

        public void cancel() {
            System.out.println("停止");
            cancelled = true;
        }
    }


}
