package com.op.concurrent.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 2017/9/26.
 */
public class TestAtomic {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        ai.addAndGet(3);
        System.out.println(ai);
        boolean b = ai.compareAndSet(3,4);
        System.out.println(b);
        System.out.println(ai);

        System.out.println("--------------------------");

        AtomicIntegerArray aia = new AtomicIntegerArray(new int[]{1,2,23,4});
        int i = aia.get(5);
        System.out.println(i);
    }
}
