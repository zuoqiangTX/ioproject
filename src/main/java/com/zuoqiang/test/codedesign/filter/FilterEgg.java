package com.zuoqiang.test.codedesign.filter;

/**
 * @author zuoqiang
 * @version 1.0
 * @description FilterEgg
 * @date 2020/6/17 9:49 上午
 */

public class FilterEgg implements Filter {

    @Override
    public void doFilter(String data) {
        System.out.println("FilterEgg进行过滤");
    }
}
