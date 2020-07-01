package thread;

import com.zuoqiang.test.customer.orm.User;

public class TestForMethod {
    public static void main(String[] args) {
        User user = new User();
        check(user);
        System.out.println(user);
        System.out.println("在方法里面new 一个对象传输无法改变参数对象的属性");
    }

    private static void check(User user) {
        User user1 = new User();
        user1.setAge("1");
        user1.setName("zuoqiang");
        user = user1;
    }
}
