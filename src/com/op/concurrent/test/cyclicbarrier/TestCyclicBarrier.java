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
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("�߳�" + Thread.currentThread().getName() + "����д������...");
            try {
                Thread.sleep(5000);      //��˯����ģ��д�����ݲ���
                System.out.println("�߳�" + Thread.currentThread().getName() + "д��������ϣ��ȴ������߳�д�����");
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
            System.out.println("�����߳�д����ϣ�����������������...");
        }
    }
}