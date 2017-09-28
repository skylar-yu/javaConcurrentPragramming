package com.op.concurrent.mytest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/28.
 */
public class TestMyBoundedBuffer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyBoundedBuffer<Integer> queue = new MyBoundedBuffer<Integer>(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyPutBlockTask<Integer>(queue));
        }
        Thread.sleep(3000);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyTakeBlockTask<Integer>(queue,list));
        }
        System.out.println("------------asdf-----------");
        while (true) {
            for (Integer i : list) {
                System.out.println("result..." + i);
            }
            Thread.sleep(2000);
        }
    }

}

class MyTakeBlockTask<E> implements Callable<E> {
    private final MyBoundedBuffer<E> myBoundedBuffer;

    private final List<E> list;

    public MyTakeBlockTask(MyBoundedBuffer myBoundedBuffer,List<E> list) {
        this.myBoundedBuffer = myBoundedBuffer;
        this.list = list;
    }

    @Override
    public E call() {
        int i =0;
        while(true){
            list.add(myBoundedBuffer.take());
            if(i>0)
                break;
        }
        return null;
    }
}

class MyPutBlockTask<E> implements Runnable {
    private final MyBoundedBuffer myBoundedBuffer;

    public MyPutBlockTask(MyBoundedBuffer myBoundedBuffer) {
        this.myBoundedBuffer = myBoundedBuffer;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("put thread.....");
            int r = new Random().nextInt();
            System.out.println("random num..." + r);
            myBoundedBuffer.put(r);
        }
    }
}
