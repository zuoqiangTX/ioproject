package com.zuoqiang.test.other;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author zuo
 */
public class Test {

    public static void main(String[] args) {
//        String s = "((优惠|抹掉|取消|能不能少一点|少还一点|减免|((把那个)&(减掉)))&!(周转))|((优惠|抹掉|取消|能不能少一点|少还一点|减免|((把那个)&(减掉)))&!(周转))";
//        String s = "((优惠|抹掉|取消|能不能少一点|少还一点|减免|((把那个)&(减掉)))&!(周转))";
//        String s = "(((只还|就还|只给|置换|指环|之患|滞缓)&(本金|本钱))&!(欠多少))";
        String s = "((电话记录|复读机|录音|循环播放)&!(我有录音|在录音))";
        System.out.println(s);
        System.out.println(parseStr(s));
    }

    private static String parseStr(String s) {
        StringBuilder str = new StringBuilder();
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c != ')') {
                //如果不是右括号，进栈
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            while (!stack.isEmpty() && stack.peek() != '(') {
                Character pop = stack.pop();
                str.append(pop);
            }
            str.append(" ");
            if (stack.peek() == '(') {
                stack.pop();
            }
        }
        return str.reverse().toString().replaceAll("&! ", "&!").replaceAll("& ", "&");
    }
}



