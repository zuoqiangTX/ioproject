package com.zuoqiang.test.tools.until;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 版本号工具类 根据数据库数值型版本号转换为字符串版本号
 * <p>
 * 初始版本号为1.0.0
 * 1级版本号为无限升级，二级版本号为10，三级版本号为20
 * </p>
 * @date 2020/1/13 2:40 下午
 */

public class NewVersionUntil {

    private static final String REGEX = "\\.";
    private static final String INIT_VERSION = "1.0.0";
    private static final String SPLIT_CHAR = ".";
    private static final Pair<Integer, Integer> THREE_LEVEL_VERSION = Pair.of(2, 21);
    private static final Pair<Integer, Integer> TWO_LEVEL_VERSION =  Pair.of(1, 11);

    private static String autoUpgradeVersion(String version) {
        if (StringUtils.isBlank(version)) {
            version = INIT_VERSION;
        }
        //将版本号拆解成整数数组
        String[] arr = version.split(REGEX);
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = Integer.valueOf(arr[i]);
        }
        //递归调用
        autoUpgradeVersion(ints, ints.length - 1);
        //数组转字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            sb.append(ints[i]);
            if ((i + 1) != ints.length) {
                sb.append(SPLIT_CHAR);
            }
        }
        return sb.toString();
    }

    /**
     * 自动升级版本号，版本号+1
     *
     * @param ints
     * @param index
     */
    private static void autoUpgradeVersion(int[] ints, int index) {
        if (index == 0) {
            ints[0] = ints[0] + 1;
        } else {
            int value = ints[index] + 1;
            if (index == THREE_LEVEL_VERSION.getKey()) {
                if (value < THREE_LEVEL_VERSION.getValue()) {
                    ints[index] = value;
                } else {
                    ints[index] = 0;
                    autoUpgradeVersion(ints, index - 1);
                }
            } else if (index == TWO_LEVEL_VERSION.getKey()) {
                if (value < TWO_LEVEL_VERSION.getValue()) {
                    ints[index] = value;
                } else {
                    ints[index] = 0;
                    autoUpgradeVersion(ints, index - 1);
                }
            }
        }
    }

    public static String transferVersion(int version) {
        //初始版本号
        String finalString = INIT_VERSION;
        while (version > 0) {
            finalString = autoUpgradeVersion(finalString);
            version--;
        }
        return finalString;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            System.out.println("测试版本号:" + i + "  转换后的版本号:" + transferVersion(i));
        }
    }
}
