package com.zuoqiang.test.proxy;

public interface IUserDao {
    void save();

    void find();
}

/**
 * 目标对象
 */
class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }

    @Override
    public void find() {
        System.out.println("模拟：查询用户");
    }
}