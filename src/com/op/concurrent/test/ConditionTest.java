package com.op.concurrent.test;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private LinkedList<String> buffer;    //����
    private int maxSize ;           //�������
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;

    ConditionTest(int maxSize){
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String string) throws InterruptedException {
        lock.lock();    //��ȡ��
        try {
            while (maxSize == buffer.size()){
                notFullCondition.await();       //���ˣ���ӵ��߳̽���ȴ�״̬
            }

            buffer.add(string);
            fullCondition.signal();
        } finally {
            lock.unlock();      //�ǵ��ͷ���
        }
    }

    public String get() throws InterruptedException {
        String string;
        lock.lock();
        try {
            while (buffer.size() == 0){
                fullCondition.await();
            }
            string = buffer.poll();
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return string;
    }
}
