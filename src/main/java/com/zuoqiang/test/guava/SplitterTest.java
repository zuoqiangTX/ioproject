package com.zuoqiang.test.guava;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Splitter 字符工具类使用 【分割】
 * @date 2020/5/6 3:22 下午
 */

public class SplitterTest {
    public static void main(String[] args) {
        String ans = "key1=123&key2=678&key3=what";

        Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(ans);
        System.out.println("split map: " + map);


        ans = "123&456&789&asdf";
        List<String> list = Splitter.on("&").splitToList(ans);
        System.out.println("split list: " + list);
    }
}
