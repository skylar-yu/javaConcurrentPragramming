package com.op.concurrent.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/9/14.
 */
public class TestDelegation {
    public static void main1(String[] args) {
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

    public static void main2(String[] args) {
        Map<String, Student> map = new HashMap<>();
        Student student = new Student();
        student.setAge(3);
        student.setName("zhangsan");
        map.put("ahah", student);

        ConcurrentMap<String, Student> conMap = new ConcurrentHashMap<>(map);

        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : conMap.entrySet()){
            System.out.println(en.getKey()+">22>>"+en.getValue());
        }

        System.out.println("-------------------------------------------------");
        Student student1 = map.get("ahah");
        student1.setAge(100);
        student1.setName("fasdfa");
//        map.put("ahah",map.get("ahah").setAge(5););
        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : conMap.entrySet()){
            System.out.println(en.getKey()+">>333>"+en.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, Student> map2 = new HashMap<>();
        Student student = new Student();
        student.setAge(3);
        student.setName("zhangsan");
        map2.put("ahah", student);

        Map<String, Student> map = Collections.unmodifiableMap(map2);

        Map<String, Student> hashMap = Collections.unmodifiableMap(new HashMap<>(map));

        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : hashMap.entrySet()){
            System.out.println(en.getKey()+">22>>"+en.getValue());
        }

        System.out.println("-------------------------------------------------");
        Student student1 = map.get("ahah");
        student1.setAge(100);
        student1.setName("fasdfa");
//        map.put("ahah",map.get("ahah").setAge(5););
        for(Map.Entry<String,Student> en : map.entrySet()){
            System.out.println(en.getKey()+"..."+en.getValue());
        }
        for(Map.Entry<String,Student> en : hashMap.entrySet()){
            System.out.println(en.getKey()+">>333>"+en.getValue());
        }
    }
}
