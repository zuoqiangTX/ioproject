package com.zuoqiang.test.codedesign.filter2;

import lombok.Data;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 责任链模式基类
 * @date 2020/6/17 10:00 上午
 */

@Data
public abstract class RuleHandler {
    // 后继节点
    protected RuleHandler successor;

    public abstract void filter(String request);

}
