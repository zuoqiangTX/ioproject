package com.zuoqiang.test.design.filter;

import com.alibaba.fastjson.JSON;
import com.zuoqiang.test.customer.orm.User;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 实名认证
 * @date 2020/8/6 6:10 下午
 */

public class AFilterImpl implements DemoFilter {
    @Override
    public void doFilter(User user, DemoFilterChain demoFilterChain) {
        System.out.println("开始实名认证" + JSON.toJSONString(user));
        if (user.getAge().equals("100")) {
            System.out.println("该用户不需要实名认证" + JSON.toJSONString(user));
            return;
        }
        System.out.println("进行实名认证" + JSON.toJSONString(user));
        boolean flag = RandomUtils.nextInt(0, 100) > 50;
        if (!flag) {
            System.out.println("实名认证不通过" + JSON.toJSONString(user));
            return;
        }
        System.out.println("实名认证通过" + JSON.toJSONString(user));
        demoFilterChain.doFilter(user);
    }
}
