package com.zuoqiang.test.tools.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试expireAfterWrites
 * @date 2020/5/7 10:20 上午
 *
 * <p>
 * 使用了expireAfterWrites之后，每次缓存失效LoadingCache都会去调用我们实现的load方法去重新加载缓存，
 * 在加载期间，所有线程对该缓存key的访问都将被block。所以如果实际加载缓存需要较长时间的话，这种方式不太适用。
 * 从代码中还可以看到，即使在CacheLoader实现了reload方法，也不会被调用，因为reload只有当设置了refreshAfterWrites时才会被调用。
 * </P>
 */

public class LoadingCacheExpiryTest {
    private LoadingCache<String, String> loadingCache;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public LoadingCacheExpiryTest() {
        loadingCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            private AtomicInteger count = new AtomicInteger(1);
            private AtomicInteger reloadCount = new AtomicInteger(1);

            @Override
            public String load(String s) throws Exception {
                System.out.println("Load Value " + count.get() + " time(s)");
                for (int i = 0; i < 10; i++) {
                    System.out.println("Load Value for " + i + " second(s)");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                count.incrementAndGet();
                return "China";
            }

            @Override
            public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                System.out.println("Reload for " + reloadCount.get() + " time(s)");
                ListenableFutureTask<String> futureTask = ListenableFutureTask.create(() -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Reload Value for " + i + " second(s)");
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    int count = reloadCount.incrementAndGet();
                    return "China" + count;
                });
                executorService.execute(futureTask);
                return futureTask;
            }
        });
    }

    public static void main(String[] args) {
        LoadingCacheExpiryTest test = new LoadingCacheExpiryTest();
        Runnable runnable1 = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Runnable1 Before Get Cache");
                    System.out.println("Runnable1 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable1 After Get Cache");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Runnable2 Before Get Cache");
                    System.out.println("Runnable2 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable2 After Get Cache");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
