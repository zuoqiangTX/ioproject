package com.zuoqiang.test.design.codedesign;

import com.zuoqiang.test.design.codedesign.filter.FilterChain;

/**
 * @author zuoqiang
 * @version 1.0
 * @description FilterHandler 使用责任链模式
 * @date 2020/6/17 9:53 上午
 */

public class FilterHandler {
    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        String data = "sss";
        filterChain.processData(data);
    }


}
