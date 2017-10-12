package com.op.concurrent.mytest.delayQueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by Administrator on 2017/10/12.
 */
class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> tasks;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> tasks) {
        this.tasks = tasks;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                tasks.take().run();//run tasks with current thread.
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
        System.out.println("Finished DelaytedTaskConsumer.");
    }
}
