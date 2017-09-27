package com.op.concurrent.mytest;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Semaphore;

@ThreadSafe
public class MySemaphoreBoundedBuffer<E> {
    private final Semaphore spaceSem;
    private final Semaphore itemSem;
    private int head = 0;
    private int tail = 0;

    private final E[] items;

    public MySemaphoreBoundedBuffer(int capacity){
        this.items = (E[]) new Object[capacity];
        spaceSem = new Semaphore(capacity);
        itemSem = new Semaphore(0);
    }

    public void put(E e) throws InterruptedException {
        spaceSem.acquire();
        addItem(e);
        itemSem.release();
    }

    public E take() throws InterruptedException {
        itemSem.acquire();
        E e = getItem();
        spaceSem.release();
        return e;
    }

    public boolean isFull(){
        return this.spaceSem.availablePermits() == items.length;
    }

    public boolean isEmpty(){
        return this.itemSem.availablePermits() == 0;
    }

    private synchronized E getItem() {
        int index = tail;
        E e = items[index];
        int nextIndex = tail+1;
        if(nextIndex == items.length) {
            tail = 0;
        } else {
            tail = nextIndex;
        }
        return e;
    }

    private synchronized void addItem(E e) {
        int index = head;
        items[index] = e;
        int nextIndex = head + 1;
        if(nextIndex == items.length) {
            head = 0;
        } else {
            head = nextIndex;
        }
    }

}
