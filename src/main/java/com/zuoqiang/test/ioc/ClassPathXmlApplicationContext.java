package com.zuoqiang.test.ioc;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author baiyue
 */
public class ClassPathXmlApplicationContext {
    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws Exception {
        // spring 加载过程 或者spring ioc实现原理
        //1、读取xml配置文件
        // 获取xml解析器
        SAXReader reader = new SAXReader();
//        Document document = reader.read(new File(xmlPath));
        // 获取当前项目路径
        Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
        //获取根节点对象
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.elements();
        Object object = null;
        for (Element sonElement : list) {
            //2、获取到每个bean的地址
            String sonBeanId = sonElement.attributeValue("id");
            if (!beanId.equals(sonBeanId)) {
                continue;
            }
            String beanClassPath = sonElement.attributeValue("class");
            //3、拿到class对象，然后进行实例化反射对象，使用反射api为私有属性赋值
            Class<?> temp = Class.forName(beanClassPath);
            object = temp.newInstance();
            //拿到成员属性
            List<Element> sonSoneleme = sonElement.elements();
            for (Element son : sonSoneleme) {
                String name = son.attributeValue("name");
                String value = son.attributeValue("value");
                // 使用反射api 为私有属性赋值
                Field field = temp.getDeclaredField(name);
                //运行往私有成员赋值
                field.setAccessible(true);
                field.set(object, value);
            }
        }

        return object;
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext appLication = new ClassPathXmlApplicationContext("user.xml");
        Object bean = appLication.getBean("user1");
        UserEntity user = (UserEntity) bean;
        System.out.println(user.getUserId() + "----" + user.getUserName());
    }
}
