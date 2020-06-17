package com.zuoqiang.test.codedesign.filter;

/**
 * @author zuoqiang
 * @version 1.0
 * @description FilterBaiCai
 * @date 2020/6/17 9:50 上午
 */

public class FilterBaiCai implements Filter {
    @Override
    public void doFilter(String data) {
        System.out.println("FilterBaiCai进行过滤");
    }
}
