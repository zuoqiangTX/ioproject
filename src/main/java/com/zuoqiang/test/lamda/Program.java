package com.zuoqiang.test.lamda;

/**
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 15:11
 */
public class Program {
    public static void main(String[] args) {

        //1、使用接口实现类
        Comparator comparator = new MyComperator();
        //2、匿名内部类
        Comparator comparator1 = new Comparator() {
            @Override
            public int compare(int a, int b) {
                return a - b;
            }
        };
        //3、lamda表达式
        Comparator comparator2 = (a, b) -> a - b;

    }


}

class MyComperator implements Comparator {
    @Override
    public int compare(int a, int b) {
        return a - b;
    }
}

@FunctionalInterface
interface Comparator {
    int compare(int a, int b);
}
