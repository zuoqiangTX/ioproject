package com.zuoqiang.test.guava;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Joiner字符工具类使用 【连接】
 * @date 2020/5/6 3:21 下午
 */

public class JoinerTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {
            {
                add("12");
                add("@#");
                add("absc");
                add(null);
            }
        };
        System.out.println(list);
//        有null的情况如果不加skipNull会直接报错
//        String ans = Joiner.on(",").join(list);
        String ans = Joiner.on(",").skipNulls().join(list);
        System.out.println("中间用','号隔开 -> join: " + ans);

        String[] strs = new String[]{"123", "456", "789", "asdf"};
        ans = Joiner.on("&").join(strs);
        System.out.println(ans);

        Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("key1", "123");
                put("key2", 678);
                put("key3", "what");
            }
        };

        ans = Joiner.on("&").withKeyValueSeparator("=").join(params);
        System.out.println("中间用'='号隔开 map-> join: " + ans);
    }
}
