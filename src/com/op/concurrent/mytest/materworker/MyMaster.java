package com.op.concurrent.mytest.materworker;

import com.bjsxt.masterworker.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/10/17.
 */
public class MyMaster {
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();
    private Map<String, Thread> workerMap = new HashMap<>();
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public MyMaster(MyWorker worker,int workerNum){
        worker.setResultMap(resultMap);
        worker.setTaskQueue(taskQueue);
        for(int i=0;i<workerNum;i++){
            workerMap.put("worker"+i,new Thread(worker));
        }
    }

    public void submit(Task task){
        taskQueue.add(task);
    }

    public void execute(){
        for(Map.Entry<String,Thread> entry : workerMap.entrySet()){
            entry.getValue().start();
        }
    }

    public boolean isCompleted(){
        for(Thread t : workerMap.values()){
            if(t.getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

}
