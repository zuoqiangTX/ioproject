package com.zuoqiang.test.design.filter;

import com.alibaba.fastjson.JSON;
import com.zuoqiang.test.customer.orm.User;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 名单筛查
 * @date 2020/8/6 6:11 下午
 */

public class BFilterImpl implements DemoFilter {
    @Override
    public void doFilter(User user, DemoFilterChain demoFilterChain) {
        System.out.println("开始名单筛查:" + JSON.toJSONString(user));
        if (user.getAge().equals("100")) {
            System.out.println("该用户不需要名单筛查" + JSON.toJSONString(user));
            return;
        }
        System.out.println("进行名单筛查" + JSON.toJSONString(user));
        boolean flag = RandomUtils.nextInt(0, 100) > 50;
        if (!flag) {
            System.out.println("名单筛查不通过" + JSON.toJSONString(user));
            return;
        }
        System.out.println("名单筛查通过" + JSON.toJSONString(user));
        demoFilterChain.doFilter(user);
    }
}
