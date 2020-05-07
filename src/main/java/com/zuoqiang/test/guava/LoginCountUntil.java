package com.zuoqiang.test.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 模拟登录失败的工具类
 * @date 2020/5/7 11:30 上午
 */

public class LoginCountUntil {
    //缓存有效期1800秒
    private int expiration = 1800;

    private static final int MAX_ATTEMPT = 10;
    private static LoadingCache<String, Integer> loginCountCache;

    public LoginCountUntil() {
        System.out.println("构造函数执行");
        loginCountCache = CacheBuilder.newBuilder().
                expireAfterWrite(expiration, TimeUnit.SECONDS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) {
                return 0;
            }
        });
    }

//    @PostConstruct
//    /**   所以当容器被spring 管理的时候再使用关键字比较好
//     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
//     */
//    private void init() {
//        System.out.println("构造函数执行以后才执行");
//        loginCountCache = CacheBuilder.newBuilder().
//                expireAfterWrite(expiration, TimeUnit.SECONDS).build(new CacheLoader<String, Integer>() {
//            @Override
//            public Integer load(String key) {
//                return 0;
//            }
//        });
//    }

    public void successLogin(String key) {
        loginCountCache.invalidate(key);
    }

    public void successFail(String key) {
        int attempts = 0;
        try {
            attempts = loginCountCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        loginCountCache.put(key, attempts);
    }

    /**
     * 如果给的key的value值大于了MAX_ATTEMPT 则表示blocked
     *
     * @param key
     * @return
     */
    public boolean isBlocked(String key) {
        try {
            return loginCountCache.get(key).compareTo(MAX_ATTEMPT) >= 0;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        LoginCountUntil until = new LoginCountUntil();
        String key = "user:login:failed:" + 23232;
        for (int i = 0; i < 10; i++) {
            until.successFail(key);
            System.out.println("是否达到限制登录次数:" + until.isBlocked(key));
        }
        System.out.println("此后又多次登录:" + until.isBlocked(key));
        until.successLogin(key);
        System.out.println("清空后 是否达到限制登录次数:" + until.isBlocked(key));
    }
}
