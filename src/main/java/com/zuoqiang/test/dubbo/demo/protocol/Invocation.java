package com.zuoqiang.test.dubbo.demo.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 运行时数据
 * @date 2020/5/13 11:19 上午
 */

@Data
public class Invocation implements Serializable {
    private static final long serialVersionUID = -9114016102880008471L;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Class[] paramsType;

    /**
     * 参数值
     */
    private Object[] values;

    public Invocation(String interfaceName, String methodName, Class[] paramsType, Object[] values) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramsType = paramsType;
        this.values = values;
    }
}
