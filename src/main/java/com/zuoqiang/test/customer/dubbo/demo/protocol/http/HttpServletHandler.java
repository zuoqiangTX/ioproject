//package com.zuoqiang.test.customer.dubbo.demo.protocol.http;
//
//
//import com.zuoqiang.test.customer.dubbo.demo.protocol.Invocation;
//import com.zuoqiang.test.customer.dubbo.demo.provider.LocalRegister;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.io.IOUtils;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * @author zuoqiang
// * @version 1.0
// * @description handler处理器
// * @date 2020/5/13 11:11 上午
// */
//
//public class HttpServletHandler {
//    public void handle(HttpServletRequest req, HttpServletResponse resp) {
//        //处理请求，返回结果
//        try {
//            ServletInputStream inputStream = req.getInputStream();
//            ObjectInputStream ois = new ObjectInputStream(inputStream);
//
//            //获取执行参数
//            Invocation invocation = (Invocation) ois.readObject();
//            //获取类
//            Class implClass = LocalRegister.get(invocation.getInterfaceName());
//            //获取方法
//            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamsType());
//            String result = (String) method.invoke(implClass.newInstance(), invocation.getValues());
//            IOUtils.write(result, resp.getOutputStream(), "utf-8");
//        } catch (IOException e) {
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
