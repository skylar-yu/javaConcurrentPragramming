package com.bjsxt.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Master-Worker模式是一种将串行任务并行化的方案，被分解的子任务在系统中可以被并行处理，
 * 同时，如果有需要，Master进程不需要等待所有子任务都完成计算，就可以根据已有的部分结果集计算最终结果集。
 */
public class Master {

	//1 有一个盛放任务的容器
	private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();
	
	//2 需要有一个盛放worker的集合
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();
	
	//3 需要有一个盛放每一个worker执行任务的结果集合
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	//4 构造方法
	public Master(Worker worker , int workerCount){
		worker.setWorkQueue(this.taskQueue);
		worker.setResultMap(this.resultMap);
		
		for(int i = 0; i < workerCount; i ++){
			this.workers.put(Integer.toString(i), new Thread(worker));
		}
		
	}
	
	//5 需要一个提交任务的方法
	public void submit(Task task){
		this.taskQueue.add(task);
	}
	
	//6 需要有一个执行的方法，启动所有的worker方法去执行任务
	public void execute(){
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			me.getValue().start();
		}
	}

	//7 判断是否运行结束的方法
	public boolean isComplete() {
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			if(me.getValue().getState() != Thread.State.TERMINATED){
				return false;
			}
		}		
		return true;
	}

	//8 计算结果方法
	public int getResult() {
		int priceResult = 0;
		for(Map.Entry<String, Object> me : resultMap.entrySet()){
			priceResult += (Integer)me.getValue();
		}
		return priceResult;
	}
}
