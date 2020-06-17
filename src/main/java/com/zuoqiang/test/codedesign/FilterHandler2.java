package com.zuoqiang.test.codedesign;

import com.zuoqiang.test.codedesign.filter2.LimitRuleHandler;
import com.zuoqiang.test.codedesign.filter2.LocationRuleHandler;
import com.zuoqiang.test.codedesign.filter2.NewUserRuleHandler;
import com.zuoqiang.test.codedesign.filter2.RuleHandler;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 是先定义好一个链表，然后在通过任意一节点后，如果此节点有后继节点，那么传递下去
 * @date 2020/6/17 10:05 上午
 */

public class FilterHandler2 {
    public static void main(String[] args) {
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();

        // 假设本次活动仅校验地区和奖品数量，不校验新老用户
        locationHandler.setSuccessor(limitHandler);

        locationHandler.filter("2");
    }
}
