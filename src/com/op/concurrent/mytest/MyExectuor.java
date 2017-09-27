package com.op.concurrent.mytest;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/9/27.
 */
public class MyExectuor implements Executor {
    private final Queue<Runnable> queue = new LinkedBlockingQueue<>();
    private Executor executor;

    public MyExectuor(Executor executor){
        this.executor = executor;
    }

    @Override
    public void execute(final Runnable r) {
//        queue.offer(new Thread(r));
        queue.offer(new Runnable() {
            @Override
            public void run() {
                r.run();
            }
        });
        Runnable task = queue.poll();
        if(task != null) {
            executor.execute(task);
        }
    }
}
