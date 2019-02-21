package com.zuoqiang.test.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json 转换s试验
 *
 * @author tongbanjie
 */
public class JsonTest {
    public static void main(String[] args) {
        //transferStringToJson();
        //transferMapOrJson();
        transferListOrJson();
    }

    /**
     * list和jsonArray的互转
     */
    private static void transferListOrJson() {
        System.out.println("jsonArray转list");
        String jsonString = "[{\"id\":1,\"age\":1},{\"id\":2,\"age\":2},{\"id\":3,\"age\":3}]";
        //student 必须有无参构造器，否则转换会失败
        List<Student> listPerson = JSON.parseArray(jsonString, Student.class);
        for (Student student : listPerson) {
            System.out.println("学生信息：" + student.getAge());
        }
        System.out.println("-------------------");

        System.out.println("list转jsonArray");
        List<Student> list = new ArrayList();
        list.add(new Student(1, 1, "z"));
        list.add(new Student(2, 2, "u"));
        list.add(new Student(3, 3, "o"));
        JSONArray ja = JSONArray.parseArray(JSON.toJSONString(list));
        //两种方法均可
//        JSONArray ja = new JSONArray(new ArrayList<Object>(list));
        System.out.println(ja.toJSONString());
        System.out.println("-------------------");
    }

    /**
     * map和json之间的互相转换
     */
    private static void transferMapOrJson() {
        System.out.println("json转map");
        String jsonMapMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";
        Map map = JSONObject.toJavaObject(JSON.parseObject(jsonMapMessage), Map.class);
        System.out.println("语文" + map.get("语文"));
        System.out.println("-------------------");

        System.out.println("map转json");
        Map testMap = new HashMap();
        testMap.put("id", 1);
        testMap.put("name", "baiyue");
        testMap.put("age", 3);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(testMap));
        System.out.println(jsonObject.toJSONString());
        System.out.println("-------------------");
    }

    /**
     * String 转换为 json对象或者jsonArray对象
     */
    private static void transferStringToJson() {
        System.out.println("String转JSONObject");
        String jsonMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";
        JSONObject myJson = JSONObject.parseObject(jsonMessage);
        System.out.println("语文" + myJson.getString("语文"));
        System.out.println("-------------------");

        System.out.println("String转JSONArray");
        String jsonArrayMessage = "[{'num':'成绩', '外语':88, '历史':65, '地理':99, 'object':{'aaa':'1111','bbb':'2222','cccc':'3333'}}," +
                "{'num':'兴趣', '外语':28, '历史':45, '地理':19, 'object':{'aaa':'11a11','bbb':'2222','cccc':'3333'}}," +
                "{'num':'爱好', '外语':48, '历史':62, '地理':39, 'object':{'aaa':'11c11','bbb':'2222','cccc':'3333'}}]";
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayMessage);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            System.out.println(i + " : " + jsonObject.toJSONString());
        }
        System.out.println("-------------------");
    }
}
