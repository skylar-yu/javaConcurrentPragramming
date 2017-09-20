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
        latch.await();      //���̵߳ȴ�����writer�߳�ִ����
    }
    static class Writer extends Thread{
        private CountDownLatch latch;
//        private CyclicBarrier cyclicBarrier;
        public Writer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("�߳�"+Thread.currentThread().getName()+"����д������...");
            try {
                Thread.sleep(5000);      //��˯����ģ��д�����ݲ���
                System.out.println("�߳�"+Thread.currentThread().getName()+"д��������ϣ��ȴ������߳�д�����");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("�����߳�д����ϣ�����������������...");
        }
    }
}