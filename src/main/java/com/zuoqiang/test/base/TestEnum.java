package com.zuoqiang.test.base;

import java.util.HashSet;
import java.util.Set;

/**
 * 枚举基本写法测试类
 *
 * @author tongbanjie
 */

public enum TestEnum {
    SHANXI("SHANXI", "陕西"),
    SICHUAN("SICHUAN", "陕西"),
    ZHEJIANG("ZHEJIANG", "浙江"),
    BEIJING("BEIJING", "北京");

    private String code;
    private String name;
    private static final Set<String> hasPandaProvice;

    static {
        hasPandaProvice = new HashSet<>();
        hasPandaProvice.add(SHANXI.code);
        hasPandaProvice.add(SICHUAN.code);
    }

    TestEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean isHasPanda(String code) {
        return hasPandaProvice.contains(code);
    }

    @Override
    public String toString() {
        return name + "|" + code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    /**
     * 通过枚举<code>value</code>获得枚举
     */
    public static String getNameByCode(String code) {
        for (TestEnum testEnum : values()) {
            if (testEnum.getCode().equals(code)) {
                return testEnum.getName();
            }
        }
        return null;
    }

    /**
     * 通过枚举<name>name</name>获取枚举code
     */
    public static String getCodeByName(String name) {
        for (TestEnum testEnum : values()) {
            if (testEnum.getName().equals(name)) {
                return testEnum.getCode();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(TestEnum.isHasPanda("SHANGHAI"));
        System.out.println(TestEnum.getNameByCode("SHANGHAI"));
        System.out.println(TestEnum.getNameByCode("SHANXI"));
        System.out.println(TestEnum.isHasPanda("SHANXI"));
        System.out.println(TestEnum.getCodeByName("陕西"));
    }

}
