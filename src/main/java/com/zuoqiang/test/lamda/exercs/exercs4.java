package com.zuoqiang.test.lamda.exercs;

import com.google.common.collect.Lists;
import com.zuoqiang.test.lamda.data.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 删除集合中满足条件的元素 foreach
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 22:28
 */
public class exercs4 {
    public static void main(String[] args) {

        List<Person> list = new ArrayList();
        list.add(new Person("ziu", 11));
        list.add(new Person("nie", 19));
        list.add(new Person("ss", 13));
        list.add(new Person("li", 12));

        //删除年龄大于13岁的
//        Iterator<Person> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Person next = iterator.next();
//            if (next.age > 13) {
//                iterator.remove();
//            }
//        }

        //lamda实现
        list.removeIf(ele -> ele.age > 13);
        System.out.println(list);


    }
}
