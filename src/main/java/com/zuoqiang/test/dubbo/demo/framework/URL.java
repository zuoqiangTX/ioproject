package com.zuoqiang.test.dubbo.demo.framework;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuoqiang
 * @version 1.0
 * @description url
 * @date 2020/5/13 10:11 上午
 */

@Data
public class URL implements Serializable {
    private String hostName;
    private int port;

    public URL(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public URL() {
    }
}
