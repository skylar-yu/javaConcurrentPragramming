package com.op.concurrent.mytest.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EnhancedThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {

    /**
     * 计数器,用于表示已经提交到队列里面的task的数量,这里task特指还未完成的task。
     * 当task执行完后,submittedTaskCount会减1的。
     */
    private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, TaskQueue workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.AbortPolicy());
        workQueue.setExecutor(this);
    }

    /**
     * 覆盖父类的afterExecute方法,当task执行完成后,将计数器减1
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        submittedTaskCount.decrementAndGet();
    }


    public int getSubmittedTaskCount() {
        return submittedTaskCount.get();
    }


    /**
     * 覆盖父类的execute方法,在任务开始执行之前,计数器加1。
     */
    @Override
    public void execute(Runnable command) {
        submittedTaskCount.incrementAndGet();
        try {
            super.execute(command);
        } catch (RejectedExecutionException rx) {
            //当发生RejectedExecutionException,尝试再次将task丢到队列里面,如果还是发生RejectedExecutionException,则直接抛出异常。
            BlockingQueue<Runnable> taskQueue = super.getQueue();
            if (taskQueue instanceof TaskQueue) {
                final TaskQueue queue = (TaskQueue) taskQueue;
                if (!queue.forceTaskIntoQueue(command)) {
                    submittedTaskCount.decrementAndGet();
                    throw new RejectedExecutionException("队列已满");
                }
            } else {
                submittedTaskCount.decrementAndGet();
                throw rx;
            }
        }
    }
}
