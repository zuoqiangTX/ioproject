package com.zuoqiang.test.design.filter;

import com.zuoqiang.test.customer.orm.User;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/8/6 6:10 下午
 */

public interface DemoFilter {
    void doFilter(User user, DemoFilterChain demoFilterChain);

}
