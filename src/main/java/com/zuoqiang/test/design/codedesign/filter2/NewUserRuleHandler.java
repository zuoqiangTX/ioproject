package com.zuoqiang.test.design.codedesign.filter2;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 校验用户是否是新用户
 * @date 2020/6/17 10:01 上午
 */

public class NewUserRuleHandler extends RuleHandler {
    @Override
    public void filter(String request) {
        if (StringUtils.equals("1", request)) {
            // 如果有后继节点的话，传递下去
            System.out.println("校验用户通过,进行下一次链路操作");
            if (this.getSuccessor() != null) {
                this.getSuccessor().filter(request);
            }
        } else {
            throw new RuntimeException("该活动仅限新用户参与");
        }
    }
}
