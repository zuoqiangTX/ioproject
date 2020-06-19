package com.zuoqiang.test.other;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 重试代码
 * @date 2020/6/19 11:41 上午
 */

@Slf4j
public class RetryTest {
    private static final int TRY_TIMES = 2;

    public static void main(String[] args) {
//        retry1("参数");
        retry2("参数");
    }

    private static String retry2(String param) {
        int i = -1;
        while (++i <= TRY_TIMES) {
            try {
                if (i > 0) {
                    log.info("进行第{}次重试,参数:{}>>>>", i, param);
                }
                doBusiness(i);
                return "success";
            } catch (Exception e) {
                log.error("结果异常，第{}次提交,参数[{}]", i + 1, param, e);
            }
        }
        return "失败";
    }

    private static String retry1(String param) {
        for (int i = 0; i <= TRY_TIMES; i++) {
            try {
                if (i > 0) {
                    log.info("进行第{}次重试,参数:{}>>>>", i, param);
                }
                doBusiness();
                return "success";
            } catch (Exception e) {
                log.error("结果异常，第{}次提交,参数[{}]", i + 1, param, e);
            }
        }
        return "失败";
    }

    private static void doBusiness(int i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        if (i < 1) {
            int a = 1 / 0;
        }
        log.info("返回正确结果");

    }

    private static void doBusiness() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        int a = 1 / 0;
    }
}
