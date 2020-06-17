package com.zuoqiang.test.codedesign.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/6/17 9:51 上午
 */

public class FilterChain {
    private List<Filter> filters = Lists.newArrayList();

    public FilterChain() {
        filters.add(new FilterAoBing());
        filters.add(new FilterEgg());
        filters.add(new FilterBaiCai());
    }

    public void processData(String data) {
        for (Filter filter : filters) {
            filter.doFilter(data);
        }
    }
}
