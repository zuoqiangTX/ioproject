package com.zuoqiang.test.other;

/**
 * 比较Integer大小
 *
 * @author baiyue
 */
public class CompareIntegerTest {

    public static void main(String[] args) {
        System.out.println("------new -----");
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println( i == j);
        Integer i1 = new Integer(100);
        Integer j1 = new Integer(300);
        System.out.println(i1 < j1);

        System.out.println("------value of -----");
        Integer i2 = Integer.valueOf(100);
        Integer j2 = Integer.valueOf(100);
        System.out.println(i2 == j2);
        Integer i3 = Integer.valueOf(400);
        Integer j3 = Integer.valueOf(400);
        System.out.println(i3 == j3);

        System.out.println("------自动装箱 -----");
        Integer i4 = 100;
        Integer j4 = 100;
        System.out.println(i4==j4);
        Integer i5 = 400;
        Integer j5 = 400;
        System.out.println(i5==j5);
        System.out.println("------推荐方法 -----");
        Integer i6 = 100;
        Integer j6 = 100;
        System.out.println(i6.equals(j6));
        System.out.println(i6.compareTo(j6));

    }

}
