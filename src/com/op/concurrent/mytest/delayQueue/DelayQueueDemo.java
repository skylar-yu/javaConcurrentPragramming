package com.op.concurrent.mytest.delayQueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/10/12.
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        int maxDelayTime = 5000;//milliseconds
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        //填充10个休眠时间随机的任务
        for (int i = 0; i < 10; i++) {
            queue.put(new DelayedTask(random.nextInt(maxDelayTime)));
        }
        //设置结束的时候。
        queue.add(new DelayedTask.EndSentinel(maxDelayTime, exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}