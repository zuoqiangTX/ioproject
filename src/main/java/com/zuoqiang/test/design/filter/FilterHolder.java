package com.zuoqiang.test.design.filter;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/8/6 6:12 下午
 */

public class FilterHolder {
    private transient DemoFilter filter;

    public DemoFilter getFilter() {
        return filter;
    }

    public void setFilter(DemoFilter filter) {
        this.filter = filter;
    }
}
