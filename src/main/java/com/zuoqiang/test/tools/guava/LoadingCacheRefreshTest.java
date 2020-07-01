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
 * @description refreshAfterWrites 异步刷新，不会阻塞
 * <p>
 * 适用了refreshAfterWrites之后，需要自己实现CacheLoader的reload方法，
 * 在方法中创建一个ListenableFutureTask，然后将这个task提交给线程池去异步执行，
 * reload方法最后返回这个ListenableFutureTask。这样做之后，缓存失效后重新加载就变成了异步，
 * 加载期间尝试获取缓存的线程也都不会被block，而是获取到加载之前的值。当加载完毕之后，各个线程就能取到最新值了。
 * </p>
 * @date 2020/5/7 10:32 上午
 */

public class LoadingCacheRefreshTest {

    public LoadingCache<String, String> loadingCache;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public LoadingCacheRefreshTest() {
        loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(5, TimeUnit.SECONDS).build(
                new CacheLoader<String, String>() {
                    private AtomicInteger count = new AtomicInteger(1);
                    private AtomicInteger reloadCount = new AtomicInteger(1);

                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("Load Value " + count.get() + " time(s)");
                        for (int i = 0; i < 10; i++) {
                            System.out.println("Load Value for " + i + " second(s)");
                            TimeUnit.MILLISECONDS.sleep(1000);
                            ;
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
                                ;
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
        LoadingCacheRefreshTest test = new LoadingCacheRefreshTest();
        Runnable runnable1 = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Runnable1 Before Get Cache");
                    System.out.println("Runnable1 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable1 After Get Cache");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    ;
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
                    ;
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
