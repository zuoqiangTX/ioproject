package com.zuoqiang.test.design.filter;

import com.zuoqiang.test.customer.orm.User;

import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/8/6 6:13 下午
 */

public class DemoFilterChain {
    FilterHolder filterHolder;
    DemoFilterChain next;

    DemoFilterChain(List<DemoFilter> list) {
        if (list.isEmpty()) {
            return;
        }
        filterHolder = new FilterHolder();
        filterHolder.setFilter(list.get(0));
        list.remove(0);
        next = new DemoFilterChain(list);
    }

    public void doFilter(User user) {
        if (filterHolder != null) {
            DemoFilter filter = filterHolder.getFilter();
            filter.doFilter(user, next);
        }
    }
}
