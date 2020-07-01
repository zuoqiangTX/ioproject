package com.zuoqiang.test.tools.guava;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Splitter 字符串切割类
 * @date 2020/5/7 1:55 下午
 */

public class Splitter2Test {
    public static void main(String[] args) {
        testSplitOnSplit();
        testSplit_On_Split_OmitEmpty();
        testSplit_On_Split_OmitEmpty_TrimResult();
        testSplitFixLength();
        testSplitOnSplitLimit();
        testSplitOnPatternString();
        testSplitOn_SplitToMap();

    }

    /**
     * 将字符串”hello=HELLO| world=WORLD|||”按照|分隔，
     * 去掉空字符串和非空字符串两端的空格，然后转化为Map类型的集合
     */
    private static void testSplitOn_SplitToMap() {
        Map<String, String> split = Splitter.on(Pattern.compile("\\|"))
                .omitEmptyStrings()
                .trimResults()
                .withKeyValueSeparator("=")
                .split("hello=HELLO| world=WORLD|||");
        System.out.println(split);
// 输出结果：
//        {hello=HELLO, world=WORLD}
    }

    /**
     * 使用正则表达式将字符串”hello|world|||”按照|分隔为单独的字符串，并且去掉空的字符串和非空字符串两端的空格
     */
    private static void testSplitOnPatternString() {
        List<String> list = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello|world|||");
        System.out.println(list);
// 输出结果：
//[hello, world]
// Splitter.onPattern(regex) 表示使用正则表达式匹配.
        List<String> result = Splitter.on(Pattern.compile("\\|"))
                .trimResults()
                .omitEmptyStrings()
                .splitToList("hello|world|||");

        System.out.println(result);
// 输出结果：
//[hello, world]
// 在Splitter.on() 中传入Pattern类型的正则
    }

    /**
     * 将字符串”hello#world#java#google#scala”按照#分割为三组，
     * 前两个单词分别为一组，最后剩余的所有字符串为单独的一组
     */
    private static void testSplitOnSplitLimit() {
        List<String> list = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
        System.out.println(list);
//         输出结果：
//[hello, world, java#google#scala]
    }

    /**
     * 将字符串”aaaabbbbccccdddd”按照固定长度4个字符为一组分割
     */
    private static void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        System.out.println(result);
        // 输出结果：
        //[aaaa, bbbb, cccc, dddd]
    }

    /**
     * 将字符串”Hello|Word”用|切为两个单独的单词
     */
    public static void testSplitOnSplit() {
        List<String> result = Splitter.on("|")
                .splitToList("Hello|World");
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        // 输出结果：
        // Hello
        // World
    }

    /**
     * 将字符串”hello#world###”用#分隔，并过滤掉空字符串
     */
    private static void testSplit_On_Split_OmitEmpty() {
        List<String> result = Splitter.on("#").omitEmptyStrings().splitToList("hello#world###");
        System.out.println(result);
        // 输出结果：
        //[hello, world]
        // omitEmptyStrings()表示过滤掉空字符串
    }

    /**
     * 将字符串”hello | world|||”使用|分隔，并去掉空字符串及非空字符串两端的空格
     */
    public static void testSplit_On_Split_OmitEmpty_TrimResult() {
        List<String> result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        System.out.println(result);
        // 输出结果：
        //[hello, world]
        // 输出结果中去掉了空字符串和hello,world两端的空格
        // trimResults()表示去掉字符串两端的空格
    }


}
