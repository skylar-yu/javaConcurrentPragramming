package com.op.concurrent.test.semaphore;

import com.op.concurrent.test.Student;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/9/26.
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Student> list = new CopyOnWriteArrayList<>();
        Student s1 = new Student(3, "zhangsan");
        Student s2 = new Student(4, "lisi");
        list.add(s1);
        list.add(s2);
        System.out.println(list);
        s1.setName("wangwu");
        System.out.println(list);
    }
}
