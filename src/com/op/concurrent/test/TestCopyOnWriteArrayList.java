package com.op.concurrent.test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/9/18.
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        list.add("asdf");
        list.add("qwer");
        list.add("zxcv");
        System.out.println(list);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            String next = (String)iterator.next();
            if ("asdf".equals(next)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
