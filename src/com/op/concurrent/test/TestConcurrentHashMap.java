package com.op.concurrent.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/9/21.
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentMap map = new ConcurrentHashMap();
        map.put("aaa","bbb");
        map.put("ccc","ddd");

        Object o = map.putIfAbsent("aaa", "ddd"); //如果存在当前key，则不添加，并返回Map中对应的value
        System.out.println(o);
        System.out.println("------------------------------");
        Object o1 = map.putIfAbsent("eee", "ffff"); //如果不存在当前key，则添加，并返回Map中对应的value（null）
        System.out.println(o1);
        System.out.println("------------------------------");
        Object o2 = map.putIfAbsent("ccc", "ddd");
        System.out.println(o2);
    }
}
