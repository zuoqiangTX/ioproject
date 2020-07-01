package com.zuoqiang.test.jdk.lamda.streams;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zuoqiang
 * @version 1.0
 * @description steam简书测试
 * <p>
 * Stream API将处理的数据源看做一种Stream（流），Stream（流）在Pipeline（管道）中传输和运算，支持的运算包含筛选、排序、聚合等，当到达终点后便得到最终的处理结果。
 * tream中的操作从概念上讲分为中间操作和终端操作：
 * <p>
 * 中间操作：例如peek()方法提供Consumer（消费）函数，但执行peek()方法时不会执行Consumer函数，而是等到流真正被消费时（终端操作时才进行消费）才会执行，这种操作为中间操作；
 * 终端操作：例如forEach()、collect()、count()等方法会对流中的元素进行消费，并执行指定的消费函数（peek方法提供的消费函数在此时执行），这种操作为终端操作。
 * 要理解中间操作和终端操作的概念，防止埋坑~
 * <p>
 * 作者：DoubleBin
 * 链接：https://www.jianshu.com/p/2b40fd0765c3
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * </p>
 * @date 2020/3/23 10:17 上午
 */

public class StreamOfTest {
    public static void main(String[] args) {
//        testForEach();
//        testMap();
//        testFlatMap();
//        testFilter();
//        testReduce();
//        testCollect();
        testsummaryStatistics();
    }

    private static void testsummaryStatistics() {
//        统计类统计数量、最大值、最小值、求和、平均值等方法（方法名和返回类型可能不同）
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, 4, 5);
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(e -> e).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getMin());
    }

    private static void testCollect() {
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, 4, 5);
        List<Integer> collect = numbers.parallelStream().collect(Collectors.toList());
//        用map()方法生成新的流，再用collect()方法返回原数组的绝对值数组。
        List<Integer> collect2 = numbers.parallelStream().map(e -> Math.abs(e)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect2);

    }

    private static void testReduce() {
        //折叠操作，将流中的所有值合成一个
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, -1, 4, 5, 1);
        Integer total = numbers.stream().reduce((t, n) -> t + n).get();
        System.out.println("active count :" + total);
    }

    private static void testFilter() {
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, 4, 5);
        System.out.println("active count:" + numbers.parallelStream().filter(e -> e.intValue() >= 0).count());

    }

    private static void testFlatMap() {
        List<String> list = Lists.newArrayList("1 2", "3 4", "5 6");
        System.out.println(">>>>>>>>>>>>>>flatMap将很多元素变成一个流>>>>>>>>>>>>>>>>>>>>>>>>");
        list.stream().flatMap(e -> Arrays.stream(e.split(" "))).forEach(e -> System.out.println("list element:" + e));
        System.out.println(">>>>>>>>>>>>>>flatMap将元素变成流中流>>>>>>>>>>>>>>>>>>>>>>>>");
        list.stream().map(e -> Arrays.stream(e.split(" "))).forEach(e -> System.out.println("list element:" + e));
        list.stream().map(e -> Arrays.stream(e.split(" "))).forEach(e -> e.forEach(ele -> System.out.println("list element:" + ele)));
    }

    private static void testMap() {
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, 4, 5);
        numbers.stream().map(e -> Math.abs(e)).forEach(e -> System.out.println("list element:" + e));
    }

    private static void testForEach() {
        List<Integer> numbers = Lists.newArrayList(-1, -2, 0, 4, 5);
        numbers.stream().forEach((e) -> System.out.println("list element:" + e));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //使用parallelStream()生成并行流后，对集合元素的遍历是无序的。
        numbers.parallelStream().forEach((e) -> System.out.println("list element:" + e));
    }
}
