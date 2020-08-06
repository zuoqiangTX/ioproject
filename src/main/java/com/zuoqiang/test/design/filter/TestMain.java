package com.zuoqiang.test.design.filter;

import com.zuoqiang.test.customer.orm.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/8/6 6:14 下午
 */

public class TestMain {
    public static void main(String[] args) {
        List<DemoFilter> list = new ArrayList<>();
        AFilterImpl aFilter = new AFilterImpl();
        BFilterImpl bFilter = new BFilterImpl();
        list.add(aFilter);
        list.add(bFilter);
        DemoFilterChain demoFilterChain = new DemoFilterChain(list);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(String.valueOf(i));
            user.setName("name:" + i);
            if (i == 0) {
                user.setAge("100");
            }
            demoFilterChain.doFilter(user);
        }
    }
}
