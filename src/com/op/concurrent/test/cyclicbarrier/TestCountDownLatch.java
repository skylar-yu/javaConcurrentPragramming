package com.op.concurrent.test.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/9/20.
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        int N = 4;
//        CyclicBarrier barrier  = new CyclicBarrier(N);
        CountDownLatch latch = new CountDownLatch(4);
        for(int i=0;i<N;i++)
            new Writer(latch).start();
        latch.await();      //主线程等待所有writer线程执行完
    }
    static class Writer extends Thread{
        private CountDownLatch latch;
//        private CyclicBarrier cyclicBarrier;
        public Writer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}