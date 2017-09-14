package com.op.concurrent.test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/9/14.
 */
public class TestDelegation {
    public static void main(String[] args) {
        ConcurrentMap<String,Student> map = new ConcurrentHashMap();
        Student student = new Student();
        student.setAge(3);
        student.setName("zhangsan");
        map.put("ahah", student);
        Map<String,Student> map1 = Collections.unmodifiableMap(map);
        System.out.println(map==map1);
        Set<Map.Entry<String,Student>> set = map.entrySet();
//        for (ConcurrentMap.Entry en : map.entrySet()) {
//
//        }
        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : map1.entrySet()){
            System.out.println(en.getKey()+">>>"+en.getValue());
        }
        System.out.println("-------------------------------------------------");
        Student student1 = map1.get("ahah");
        student1.setAge(100);
        student1.setName("fasdfa");
//        map.put("ahah",map.get("ahah").setAge(5););
        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : map1.entrySet()){
            System.out.println(en.getKey()+">>>"+en.getValue());
        }
//        map1.
    }
}
