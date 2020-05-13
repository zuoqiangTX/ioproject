package com.zuoqiang.test.dubbo.demo.protocol.http;

import jakarta.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/13 11:03 上午
 */

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, IOException {
        new HttpServletHandler().handle(req, resp);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
