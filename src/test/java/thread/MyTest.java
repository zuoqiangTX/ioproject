package thread;

/**
 * ---------------------
 * 作者：皂白
 * 来源：CSDN
 * 原文：https://blog.csdn.net/nextyu/article/details/79039566
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class MyTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable, "线程1");
        Thread thread2 = new Thread(myRunnable, "线程2");
        Thread thread3 = new Thread(myRunnable, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
