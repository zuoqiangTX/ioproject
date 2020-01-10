package com.zuoqiang.test.until;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 版本号工具类
 * @date 2020/1/10 4:08 下午
 */

public class VersionUntil {


    /**
     * 字符版本号递增
     *
     * @param version
     * @return
     */
    private static String autoUpgradeVersion(String version) {
        if (StringUtils.isBlank(version)) {
            version = "0.0.0.0";
        }
        //将版本号拆解成整数数组
        String[] arr = version.split("\\.");
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
                sb.append(".");
            }
        }
        return sb.toString();
    }


    /**
     * 递增字符数组
     *
     * @param ints
     * @param index
     */
    private static void autoUpgradeVersion(int[] ints, int index) {
        if (index == 0) {
            ints[0] = ints[0] + 1;
        } else {
            int value = ints[index] + 1;
            if (value < 10) {
                ints[index] = value;
            } else {
                ints[index] = 0;
                autoUpgradeVersion(ints, index - 1);
            }
        }
    }

    /**
     * 将数字版本号转换为字符版本号
     *
     * @param version
     * @return
     */
    public static String transferVersion(int version) {
        if (version >= 10000) {
            throw new RuntimeException(String.format("不支持版本号%s,最大只支持四位版本号", version));
        }
        String finalString = "0.0.0.0";
        for (int i = 0; i < version; i++) {
            finalString = autoUpgradeVersion(finalString);
        }
        return finalString;
    }

    public static void main(String[] args) {
        int version = 9999;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(transferVersion(version));
        stopWatch.stop();
        System.out.println(stopWatch.getTime() + "ms");
//        String version = "1.0.2";
//        for (int i = 0; i < 100; i++) {
//            version = autoUpgradeVersion(version);
//            System.out.println("递增后的版本为" + version);
//        }
    }
}
