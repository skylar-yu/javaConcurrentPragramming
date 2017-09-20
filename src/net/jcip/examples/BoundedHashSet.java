package net.jcip.examples;

import java.util.*;
import java.util.concurrent.*;

/**
 * BoundedHashSet
 * <p/>
 * Using Semaphore to bound a collection
 *
 * @author Brian Goetz and Tim Peierls
 */
public class BoundedHashSet <T> {
    private final Set<T> set;   // 假定能承载的请求数
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();     //判断是否还能继续承载请求，如果不能，阻塞
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)       //如果成功释放了请求，则将一个permit释放到semaphore中
            sem.release();
        return wasRemoved;
    }
}
