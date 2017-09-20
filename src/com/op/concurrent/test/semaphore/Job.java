package com.op.concurrent.test.semaphore;

/**
 * Created by Administrator on 2017/9/19.
 */
//8.   创建一个名为Job的类并一定实现Runnable 接口。这个类实现把文档传送到打印机的任务。
public class Job implements Runnable {

    //9.   声明一个对象为PrintQueue，名为printQueue。
    private PrintQueue printQueue;

//10. 实现类的构造函数，初始化这个类里的PrintQueue对象。

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    //11. 实现方法run()。
    @Override

    public void run() {

//12. 首先， 此方法写信息到操控台表明任务已经开始执行了。
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

//13. 然后，调用PrintQueue 对象的printJob()方法。
        printQueue.printJob(new Object());

//14. 最后， 此方法写信息到操控台表明它已经结束运行了。
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}