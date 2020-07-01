package com.zuoqiang.test.customer.orm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 表的别名
 */
@Retention(RetentionPolicy.RUNTIME)  //允许反射
public @interface SetTable {
    String value();
}