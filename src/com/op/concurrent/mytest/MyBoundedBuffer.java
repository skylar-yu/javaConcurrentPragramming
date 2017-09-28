package com.op.concurrent.mytest;

/**
 * Created by Administrator on 2017/9/28.
 */
public class MyBoundedBuffer<E> {
    private final E[] items;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    public MyBoundedBuffer(int capacity){
        this.items = (E[]) new Object[capacity];
    }
    //take a element ,if queue is emptyp,blocked
    public synchronized E take() {
        while(ifEmpty()){
            try {
                System.out.println("为空，等待。。。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int index = head;
        int nextIndex = head+1;
        if(nextIndex==items.length){
            nextIndex = 0;
        }
        head = nextIndex;
        --size;
        System.out.println("size>>>>"+size);
        notifyAll();
        return items[index];
    }

    public synchronized void put(E e) {
        while (isFull()){
            try {
                System.out.println("满了，等待。。。");
                wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        int index = tail;
        items[index] = e;
        ++size;
        int nextIndex = tail + 1;
        if(nextIndex==items.length){
            nextIndex = 0;
        }
        tail = nextIndex;
        notifyAll();
    }

    public synchronized boolean isFull(){
        return size==items.length;
    }

    public synchronized boolean ifEmpty(){
        return size == 0;
    }

}
