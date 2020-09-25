package com.zuoqiang.test.other.object;

import com.alibaba.fastjson.JSON;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试当一个josnstr多传输属性的时候，对面解析对象是否有问题
 * @date 2020/9/25 2:14 下午
 */

public class JSONTest {
    public static void main(String[] args) {
        String jsonStr = "{\"createTimeStart\":\"2017-01-01 00:00:00\",\"createTimeEnd\":\"2017-12-31 23:59:59\",\"custID\":\"xxxx\"}";
        TestObj obj = JSON.parseObject(jsonStr, TestObj.class);
        System.out.println(obj.getCustID());
        System.out.println(JSON.toJSONString(obj));
    }
}
