package com.op.concurrent.mytest;

import java.util.concurrent.atomic.AtomicReference;
//https://www.ibm.com/developerworks/cn/java/j-jtp04186/

/**
 * 非阻塞算法简介
 * @param <E>
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(new Node<E>(null, null));
    private AtomicReference<Node<E>> tail = head;

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> currentNode = tail.get();
            Node<E> nextPoint = currentNode.next.get();
            //此处判断是为了，两个线程同时进入该方法，第一个线程成功执行下面的代码；第二个线程才开始执行
            //不判断，if(nextPoint==null)成立，但是if(currentNode.next.compareAndSet(null, newNode))不成立
            //所以此处判断只是为了节省性能
            //所以此处判断是为了保证当前线程获取到的链表的静止状态是唯一的，并不是当前其他线程执行成功执行同样的方法后的静止状态
            if (currentNode == tail.get()) {
                //判断是否为中间状态
                if (nextPoint == null) { /* A */
                    //不为中间状态
                    if (currentNode.next.compareAndSet(null, newNode)) {/* C */
                        tail.compareAndSet(currentNode, newNode) /* D */;
                        return true;
                    }
                } else {
                    //为中间状态
                    tail.compareAndSet(currentNode, nextPoint) /* B */;
                }
            }
        }
    }
    /**
     *  插入节点到尾部 包含两个CAS操作： 1.将新节点连接到尾部  2.tail指向新的节点
     *  ThreadA想要插入节点到尾部；
     */
}