package com.zuoqiang.test.customer.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        //项目中使用注解，肯定用到反射。反射 jdbc，ioc，常用框架，一些注解的实现
        Class<?> forName = Class.forName("com.zuoqiang.test.customer.orm.User");
        //表示该类用到了哪些注解【类级别的注解】
        Annotation[] annotations = forName.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        Field[] fields = forName.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append(" select ");


        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            SetProperty setProperty = field.getAnnotation(SetProperty.class);
            //属性名称
            String propertyName = setProperty.name();
            //属性长度
            int length = setProperty.length();
            //拼接属性
            sb.append(propertyName);
            if (i == fields.length - 1) {
                sb.append(" from ");
            } else {
                sb.append(" , ");
            }
        }

        //获取某个注解对象
        SetTable setTable = forName.getAnnotation(SetTable.class);
        //表的名称
        String tableName = setTable.value();
//        System.out.println("表的名称" + setTable.value());
        sb.append(" " + tableName + ";");
        //生成orm 框架sql语句 拼接sql语句
        System.out.println(sb.toString());

    }
}
