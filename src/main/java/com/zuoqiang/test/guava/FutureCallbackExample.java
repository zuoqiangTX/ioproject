package com.zuoqiang.test.guava;


import com.google.common.util.concurrent.*;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author zuoqiang
 * @version 1.0
 * @description google Guava包的ListenableFuture解析
 * @date 2020/6/16 4:49 下午
 */
public class FutureCallbackExample {

    public static void main(String[] args) throws Exception {
//        nativeFuture();
//        Thread.sleep(3000L);
//
//        guavaFuture();
//        Thread.sleep(3000L);
////
//        guavaFuture2();
//
//        myTest();

        myTest2();
    }

    /**
     * 错误实例ListenableFutureTask.create仅能用在futureTask中
     *
     * @throws IOException
     */
    private static void myTest2() throws IOException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> future = executorService.submit(() -> {
//            System.out.println("i am work now!");
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "1";
//        });
//        ListenableFutureTask task = ListenableFutureTask.create(future);
//        task.addListener(() -> {
//            try {
//                System.out.println(String.format("拿到结果%s", task.get()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, Executors.newSingleThreadExecutor(new ThreadFactory() {
//            private static final String THREAD_FORAMT = "自定义线程池-子线程-%d";
//            private final AtomicLong atomicLong = new AtomicLong();
//
//            @Override
//            public Thread newThread(Runnable r) {
//                String name = String.format(THREAD_FORAMT, atomicLong.getAndIncrement());
//                return new Thread(r, name);
//            }
//        }));
//        Futures.addCallback(task, new FutureCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                try {
//                    System.out.println(String.format("拿到结果%s", task.get()));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
        System.in.read();
    }

    private static void myTest() {

//        AbstractThreadPoolComponent threadPoolComponent = new AbstractThreadPoolComponent();
        ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator((Executors.newSingleThreadExecutor()));
        final ListenableFuture<String> listenableFuture = guavaExecutor.submit(() -> {
            System.out.println("i am work now!");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        //不指定线程池的话，默认和异步执行的是一个线程
        listenableFuture.addListener(() -> {
            try {
                System.out.println("异步执行的结果为" + listenableFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());
    }


    /**
     * 原生的future比较low，get那一步其实还是阻塞的
     *
     * @throws Exception
     */
    public static void nativeFuture() throws Exception {
        // 原生的Future模式,实现异步
        ExecutorService nativeExecutor = Executors.newSingleThreadExecutor();
        Future<String> nativeFuture = nativeExecutor
                .submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        // 使用sleep模拟调用耗时
                        TimeUnit.SECONDS.sleep(1);
                        return "[" + Thread.currentThread().getName()
                                + "]: 并发包Future返回结果";
                    }
                });
        // Future只实现了异步，而没有实现回调.所以此时主线程get结果时阻塞.或者可以轮训以
        //便获取异步调用是否完成
        System.out.println("[" + Thread.currentThread().getName() + "]====>" + nativeFuture.get());
        nativeExecutor.shutdown();

    }

    /**
     * guava的异步，实现了回调
     *
     * @throws Exception
     */
    public static void guavaFuture() throws Exception {
        System.out.println("-------------------------------- 神秘的分割线 -----------------------------------");
        // 好的实现应该是提供回调,即异步调用完成后,可以直接回调.本例采用guava提供的异步回调接口,方便很多.
        ListeningExecutorService guavaExecutor = MoreExecutors
                .listeningDecorator(Executors.newSingleThreadExecutor());
        final ListenableFuture<String> listenableFuture = guavaExecutor
                .submit(new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        TimeUnit.SECONDS.sleep(1);
                        return "[" + Thread.currentThread().getName()
                                + "]: guava的Future返回结果";
                    }
                });

        // 注册监听器,即异步调用完成时会在指定的线程池中执行注册的监听器
        listenableFuture.addListener(() -> {
            try {
                String logTxt = "[" + Thread.currentThread().getName()
                        + "]: guava对返回结果进行异步CallBack(Runnable):"
                        + listenableFuture.get();
                System.out.println(logTxt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());

        // 主线程可以继续执行,异步完成后会执行注册的监听器任务.
        System.out.println("[" + Thread.currentThread().getName() + "]: guavaFuture1执行结束,主线程没有阻塞");
        guavaExecutor.shutdown();
    }

    /**
     * guava还提供了FutureCallback，可以处理异常的情况
     *
     * @throws Exception
     */
    public static void guavaFuture2() throws Exception {
        System.out.println("-------------------------------- 神秘的分割线 -----------------------------------");
        // 除了ListenableFuture,guava还提供了FutureCallback接口,相对来说更加方便一些.
        ListeningExecutorService guavaExecutor2 = MoreExecutors
                .listeningDecorator(Executors.newSingleThreadExecutor());
        final ListenableFuture<String> listenableFuture2 = guavaExecutor2
                .submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        TimeUnit.SECONDS.sleep(1);
                        String logText = "[" + Thread.currentThread().getName()
                                + "]: guava的Future返回结果";
                        System.out.println(logText);
//                        int i = 1 / 0;  //测试onfailuer的情况
                        return logText;
                    }
                });


        // 注意这里没用指定执行回调的线程池,从输出可以看出，默认是和执行异步操作的线程是同一个
        Futures.addCallback(listenableFuture2, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String logTxt = "[" + Thread.currentThread().getName()
                                + "]=======>对回调结果【" + result + "】进行FutureCallback,经测试，发现是和回调结果处理线程为同一个线程";
                        System.out.println(logTxt);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println(String.format("异常结果为:%s", t.getMessage()));
                    }
                }
        );
        // 主线程可以继续执行,异步完成后会执行注册的监听器任务.
        System.out.println("[" + Thread.currentThread().getName() + "]: guavaFuture2执行结束");
        guavaExecutor2.shutdown();
    }
}