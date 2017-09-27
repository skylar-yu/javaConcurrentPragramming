package com.op.concurrent.test;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/26.
 */
public class TestThreadCallable <K> {
    public static void main(String[] args) {
        Executor executor = null;
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        ExecutorService es = null;
        Future<Integer> submit = es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        FutureTask<Integer> futureTask = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });

        es.submit(futureTask);

        FutureTask<Long> ft2 = new FutureTask(new Runnable() {
            @Override
            public void run() {

            }
        }, 3L);
    }
}
