package com.op.concurrent.test.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/9/20.
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        System.out.println("current Thread is main? :" + Thread.currentThread().getName());
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("current Thread is :" + Thread.currentThread().getName());
            }
        });
        for(int i=0;i<N;i++) {
            if(i<N-1)
                new Writer(barrier).start();
            else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }

//        System.out.println("以下让cyclicBarrier再重用一次。。。");
//        for(int i=0;i<N;i++) {
//            new Writer(barrier).start();
//        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                try {
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}