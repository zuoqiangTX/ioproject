package com.zuoqiang.test.design.proxy;

/**
 * 静态代理 代理类（1、实现目标类的接口 2、持有目标类的对象）
 *
 * @author zuoqiang
 */
public class StaticProxy {
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoProxy();
        userDao.save();
    }
}
