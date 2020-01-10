package com.zuoqiang.test.apache;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * RandomStringUtils工具类的使用
 *
 * @author baiyue
 */
public class RandomsUntil {
    public static void main(String[] args) {
        //产生5位长度的随机字符串，中文环境下是乱码
        System.out.println(RandomStringUtils.random(5));

        //使用指定的字符生成5位长度的随机字符串
        System.out.println(RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', 'e', 'f', '1', '2', '3'}));

        //生成指定长度的字母和数字的随机组合字符串
        System.out.println(RandomStringUtils.randomAlphanumeric(5));

        //生成随机数字字符串
        System.out.println(RandomStringUtils.randomNumeric(5));

        //生成随机[a-z]字符串，包含大小写
        System.out.println(RandomStringUtils.randomAlphabetic(5));

        //生成从ASCII 32到126组成的随机字符串
        System.out.println(RandomStringUtils.randomAscii(4));
    }

}
