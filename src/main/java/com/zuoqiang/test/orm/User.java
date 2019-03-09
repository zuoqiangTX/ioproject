package com.zuoqiang.test.orm;

/**
 * 实体类
 */
@SetTable("user_table")
public class User {
    @SetProperty(name = "user_name", length = 10)
    private String name;
    @SetProperty(name = "user_age", length = 10)
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}