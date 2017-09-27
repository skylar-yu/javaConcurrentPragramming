package com.op.concurrent.mytest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/27.
 */
public class TestMySemaphoreBoundedBuffer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MySemaphoreBoundedBuffer buffer = new MySemaphoreBoundedBuffer(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        List<Future<Integer>> resultList = new ArrayList<>();
        for(int i = 0 ;i<5;i++) {
            executorService.execute(new MyPutTask(buffer));
        }
        for(int i = 0 ;i<5;i++) {
            resultList.add(executorService2.submit(new MyTakeTask(buffer)));
        }

        System.out.println("-----------------------");
//        while(true){
            for(Future<Integer> f : resultList){
                System.out.println(">>>...."+ f.get());
            }
//        }
    }
}

class MyPutTask implements Runnable {
    private final MySemaphoreBoundedBuffer buffer;
    public MyPutTask(MySemaphoreBoundedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            int i = new Random().nextInt();
            System.out.println("put...."+i);
            buffer.put(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTakeTask implements Callable<Integer> {
    private final MySemaphoreBoundedBuffer buffer;
    public MyTakeTask(MySemaphoreBoundedBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public Integer call() throws Exception {
        return (Integer) buffer.take();
    }
}