//package com.zuoqiang.test.customer.dubbo.demo.protocol.http;
//
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.Server;
//import org.apache.catalina.Service;
//import org.apache.catalina.connector.Connector;
//import org.apache.catalina.core.StandardContext;
//import org.apache.catalina.core.StandardEngine;
//import org.apache.catalina.core.StandardHost;
//import org.apache.catalina.startup.Tomcat;
//
///**
// * @author zuoqiang
// * @version 1.0
// * @description HttpServer服务器，用于网络传输数据
// * @date 2020/5/13 11:03 上午
// */
//
//public class HttpServer {
//    public void start(String hostname, int port) {
//        /**
//         * Tomcat容器层次关系
//         * Tomcat -> Server -> Service -> Connector/Engine -> Host -> Content*/
//        Tomcat tomcat = new Tomcat();
//        Server server = tomcat.getServer();
//        Service service = server.findService("Tomcat");
//
//        Connector connector = new Connector();
//        connector.setPort(port);
//
//        StandardEngine engine = new StandardEngine();
//        engine.setDefaultHost(hostname);
//
//        StandardHost host = new StandardHost();
//        host.setName(hostname);
//
//        String contextPath = "";
//        StandardContext context = new StandardContext();
//        context.setPath(contextPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        host.addChild(context);
//        engine.addChild(host);
//
//        service.setContainer(engine);
//        service.addConnector(connector);
//
//        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
//        context.addServletMappingDecoded("/*", "dispatcher");
//
//        try {
//            tomcat.start();
//            tomcat.getServer().await();
//        } catch (LifecycleException e) {
//            e.printStackTrace();
//        }
//    }
//}
