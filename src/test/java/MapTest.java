import java.util.HashMap;
import java.util.Map;

/**
 * 该方法用来追加另一个Map对象到当前Map集合对象，它会把另一个Map集合对象中的所有内容添加到当前Map集合对象。
 * <p>
 * 语法  putAll(Map<? extends K,? extends V> m)
 * </p>
 */
public class MapTest {
    public static void main(String[] args) {
//        testPutAll();
        testPutIfAbsent();
    }

    /**
     * 测试PutIfAbsent
     */
    private static void testPutIfAbsent() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ZhangSan");
        map.put(2, "LiSi");
        //会覆盖旧的
        map.put(1, "WangWu");
        map.forEach((key, value) -> {
            System.out.println("key : " + key + " , value : " + value);
        });

        /**
         * putIfAbsent
         */
        Map<Integer, String> putIfAbsentMap = new HashMap<>();
        putIfAbsentMap.putIfAbsent(1, "张三");
        putIfAbsentMap.putIfAbsent(2, "李四");
        //这一条已经存在的不会被覆盖
        putIfAbsentMap.putIfAbsent(1, "王五");
        putIfAbsentMap.forEach((key, value) -> {
            System.out.println("key : " + key + " , value : " + value);
        });
    }

    /**
     * 测试putAll方法
     */
    private static void testPutAll() {
        Map map1 = new HashMap();      //定义Map集合对象
        map1.put("apple", "新鲜的苹果");     //向集合中添加对象
        map1.put("computer", "配置优良的计算机");
        map1.put("book", "堆积成山的图书");
        System.out.println("第一个Map集合大小为：" + map1.size()); //输出集合长度
        Map map2 = new HashMap();      //定义Map集合map2
        map2.put("apple2", "新鲜的苹果");     //向集合中添加对象
        map2.put("computer2", "配置优良的计算机");
        map2.put("book", "堆积成山的图书");
        System.out.println("第二个Map集合大小为：" + map2.size()); //输出集合长度
        System.out.println("把第二个Map集合添加到第一个Map集合中");
        map1.putAll(map2);        //将map2中的对象添加到map1中
        System.out.println("整合后的第一个Map集合大小为：" + map1.size());

        //整合后的Map集合大小是5而不是6，那是因为两个Map集合中有一个重复的键名“book”，Map集合的键名是不能重复的，所以新的“book”键值取代了旧的“book”键值。
        System.out.println(map1.toString());
    }

}
