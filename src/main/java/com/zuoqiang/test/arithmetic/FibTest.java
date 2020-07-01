package com.zuoqiang.test.arithmetic;

/**
 * 测试递归
 * <p>
 * fn = f(n-2) +f(n-1)
 * <p>
 * 斐波那契数列（Fibonacci sequence）的定义：斐波那契数列指的是这样一个数列 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368........，这个数列从第3项开始，每一项都等于前两项之和。
 * <p>
 *  斐波那契数列又称黄金分割数列、因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，故又称为“兔子数列”。在数学上，斐波纳契数列以如下被以递归的方法定义：F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）。
 * <p>
 * <p>
 * ————————————————
 * 版权声明：本文为CSDN博主「FERDIII」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/ferdinand1993/article/details/79683965
 *
 * @author zuoqiang
 */
public class FibTest {
    public static int fbi(int num) {
        if (num == 1 || num == 2) {
            return 1;
        }
        return fbi(num - 2) + fbi(num - 1);
    }

    public static void main(String[] args) {
        //建立for循环打印10个数字
        for (int i = 1; i <= 10; i++) {
            System.out.print(fbi(i));
            if (i != 10) {
                System.out.print(" , ");
            }
        }
    }
}
