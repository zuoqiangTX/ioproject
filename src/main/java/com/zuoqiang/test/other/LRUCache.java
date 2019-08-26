package com.zuoqiang.test.other;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap是继承于HashMap，是基于HashMap和双向链表来实现的。
 * HashMap无序；LinkedHashMap有序，可分为插入顺序和访问顺序两种。如果是访问顺序，那put和get操作已存在的Entry时，都会把Entry移动到双向链表的表尾(其实是先删除再插入)。
 * LinkedHashMap存取数据，还是跟HashMap一样使用的Entry[]的方式，双向链表只是为了保证顺序。
 * LinkedHashMap是线程不安全的。
 * <p>
 * 作者：艺旭家
 * 链接：https://www.jianshu.com/p/8f4f58b4b8ab
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 *
 * @author baiyue
 */
public class LRUCache {
    private int size;
    private Map<Integer, Integer> cache;

    public LRUCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap<Integer, Integer>(size, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > size;
            }
        };
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    public Integer get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            return -1;
        }


    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println(lruCache);
        lruCache.get(3);
        System.out.println(lruCache);
        System.out.println("put 和get都是移动到最后面！！！");


    }
}
