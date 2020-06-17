package com.zuoqiang.test.codedesign.filter2;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 校验用户所在地区是否可以参与：
 * @date 2020/6/17 10:03 上午
 */

public class LocationRuleHandler extends RuleHandler {
    @Override
    public void filter(String request) {
        boolean allowed = request.equals("2");
        if (allowed) {
            System.out.println("校验地区通过,进行下一次链路操作");
            if (this.getSuccessor() != null) {
                this.getSuccessor().filter(request);
            }
        } else {
            throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
        }
    }
}
