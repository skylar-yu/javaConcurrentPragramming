package com.op.concurrent.mytest.materworker;

import com.bjsxt.masterworker.Task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/10/17.
 */
public class MyWorker implements Runnable {
    private ConcurrentLinkedQueue<Task> taskQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    public MyWorker(ConcurrentLinkedQueue<Task> taskQueue, ConcurrentHashMap<String, Object> resultMap){
        this.taskQueue = taskQueue;
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while(true){
            Task task = taskQueue.poll();
            if(task==null){
                break;
            }
            resultMap.put(Integer.toString(task.getId()),task.getPrice());
        }
    }

    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
