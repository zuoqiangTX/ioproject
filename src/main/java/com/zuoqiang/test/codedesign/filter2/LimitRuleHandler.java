package com.zuoqiang.test.codedesign.filter2;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 限额责任链
 * @date 2020/6/17 10:04 上午
 */

public class LimitRuleHandler extends RuleHandler {
    @Override
    public void filter(String request) {
        int remainedTimes = request.length(); // 查询剩余奖品
        if (remainedTimes > 0) {
            System.out.println("校验限额通过,进行下一次链路操作");
            if (this.getSuccessor() != null) {
                this.getSuccessor().filter(request);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}
