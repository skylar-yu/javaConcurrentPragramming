package com.op.concurrent.test.queue;


import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/9/25.
 */
public class TestLinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(2);
        queue.add("aa");
        queue.add("bb");
        boolean dd = queue.offer("dd");
        queue.put("cc");
//        System.out.println(cc);
    }
}
