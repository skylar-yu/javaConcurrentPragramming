package com.op.concurrent.test.semaphore;

/**
 * Created by Administrator on 2017/9/19.
 */
//15. 实现例子的main类，创建名为 Main的类并实现main()方法。

public class Main {

    public static void main(String args[]) {

//16. 创建PrintQueue对象名为printQueue。
        PrintQueue printQueue = new PrintQueue();

//17. 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

//18. 最后，开始这10个线程们。
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }

    }
}