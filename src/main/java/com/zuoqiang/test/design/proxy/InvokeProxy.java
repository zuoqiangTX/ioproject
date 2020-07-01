package com.zuoqiang.test.design.proxy;

/**
 * 动态代理 InvocationHandler
 * <p>
 * IUserDao proxy = (IUserDao)new ProxyFactory(target).getProxyInstance();
 * 其实是 JDK 动态生成了一个类去实现接口，隐藏了这个过程：
 * class $jdkProxy implements IUserDao{}
 * </p>
 * <p>
 * 使用 JDK 生成的动态代理的前提是目标类必须有实现的接口
 * 否则使用以 CGLIB 代理（CGLIB 是以动态生成的子类继承目标的方式实现），要求目标类不能为 final 修饰
 * 因为 final 修饰的类不能被继承。
 * <p>
 */
public class InvokeProxy {
    public static void main(String[] args) {
        IUserDao target = new UserDao();
        System.out.println("目标对象：" + target.getClass());
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("代理对象：" + proxy.getClass());
        //执行代理对象的方法
        proxy.save();
    }
}
