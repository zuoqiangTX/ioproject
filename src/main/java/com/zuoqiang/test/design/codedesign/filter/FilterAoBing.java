package com.zuoqiang.test.design.codedesign.filter;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/6/17 9:50 上午
 */

public class FilterAoBing implements Filter {
    @Override
    public void doFilter(String data) {
        System.out.println("FilterAoBing进行过滤");
    }
}
