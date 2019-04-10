package thread;

import com.google.common.collect.Lists;
import com.zuoqiang.test.orm.User;

import java.util.List;

public class TestFor {
    public static void main(String[] args) {
        List<User> userList = null;
        List<String> o = Lists.newArrayList();
        System.out.println("如果为null，for循环会报空指针NULL");
        for (User user : userList) {
            o.add("zuo");
        }
    }
}
