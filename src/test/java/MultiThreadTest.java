import java.util.concurrent.CountDownLatch;

/**
 * http://annan211.iteye.com/blog/2338429http://annan211.iteye.com/blog/2338429
 * /**更新库存
 * 基于数据库乐观锁机制的秒杀
 * 基于数据库乐观锁机制的秒杀,主要是通过控制数据版本修改数据
 * int count = update goods set count = count - #{buy_count}
 * where count - #{buy_count} >=0 and code = #{code}
 * 优点: 简单、准确 可靠性高
 * 缺点: 并发低 ,基于DDS机械硬盘的并发约为 700,HDS 并发约为 300 这是一个平均值。
 * @param code 商品代码
 * @param count 购买数量
 * @return
 */
public class MultiThreadTest {

    //启动线程数
    private CountDownLatch latch = new CountDownLatch(100);
    private Integer allCount = 0;
    //顾客数量
    private Integer manCount = 0;


    public void test() {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Customer(3));
            t.start();
            latch.countDown();  //准备好了，告诉他
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("卖出去商品------"+allCount+"*****顾客数量--------"+manCount);

    }


    private class Customer implements Runnable {
        //每个客户购买数量
        private Integer count;

        public Customer(Integer count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
                //线程等待
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //理论上应该是我的方法
            boolean result = true;
            System.out.println(Thread.currentThread().getId());
            if (result) {
                synchronized (allCount) {
                    allCount += count;
                    manCount++;
                }
            }

        }
    }

    public static void main(String[] args) {
        MultiThreadTest threadTest = new MultiThreadTest();
        threadTest.test();
    }
}
