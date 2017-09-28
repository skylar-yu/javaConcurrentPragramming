package com.op.concurrent.mytest;

import net.jcip.examples.SleepyBoundedBuffer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/28.
 */
public class TestMyBoundedBuffer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyBoundedBuffer<Integer> queue = new MyBoundedBuffer<Integer>(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            executorService.execute(new MyPutBlockTask<Integer>(queue));
        }
        Thread.sleep(3000);
        ArrayList<Future<Integer>> list = new ArrayList<>();
        for(int i = 0;i<5;i++) {
//            Future<Integer> result = ;
            list.add(executorService.submit(new MyTakeBlockTask<Integer>(queue)));
        }
        System.out.println("------------asdf-----------");
        while(true){
            for(Future<Integer> f : list){
                System.out.println("result..."+f.get());
            }
            Thread.sleep(2000);
        }
    }

}
class MyTakeBlockTask<E> implements Callable<E> {
    private final MyBoundedBuffer<E> myBoundedBuffer;
    public MyTakeBlockTask(MyBoundedBuffer myBoundedBuffer){
        this.myBoundedBuffer = myBoundedBuffer;
    }
    @Override
    public E call() {
        return myBoundedBuffer.take();
    }
}
class MyPutBlockTask<E> implements Runnable {
    private final MyBoundedBuffer myBoundedBuffer;
    public MyPutBlockTask(MyBoundedBuffer myBoundedBuffer){
        this.myBoundedBuffer = myBoundedBuffer;
    }

    @Override
    public void run() {
        System.out.println("put thread.....");
        int r = new Random().nextInt();
        System.out.println("random num..."+r);
        myBoundedBuffer.put(r);
    }
}
