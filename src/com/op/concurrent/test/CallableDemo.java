package com.op.concurrent.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {
        public static void main(String[] args) { 
                ExecutorService executorService = Executors.newCachedThreadPool();
                List<Future<String>> resultList = new ArrayList<Future<String>>();

                //����10������ִ�� 
                for (int i = 0; i < 10; i++) { 
                        //ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������ 
                        Future<String> future = executorService.submit(new TaskWithResult(i)); 
                        //������ִ�н���洢��List�� 
                        resultList.add(future); 
                } 

                //��������Ľ�� 
                for (Future<String> fs : resultList) { 
                        try { 
                                System.out.println(fs.get());     //��ӡ�����̣߳�����ִ�еĽ�� 
                        } catch (InterruptedException e) { 
                                e.printStackTrace(); 
                        } catch (ExecutionException e) {
                                e.printStackTrace(); 
                        } finally { 
                                //����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�����������������Ѿ��رգ������û���������á� 
                                executorService.shutdown(); 
                        } 
                } 
        } 
} 


class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) { 
                this.id = id; 
        } 

        /** 
         * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С� 
         * 
         * @return 
         * @throws Exception 
         */ 
        public String call() throws Exception { 
                System.out.println("jinru ......start to do something---             " + Thread.currentThread().getName());
                if (new Random().nextBoolean())
                        throw new TaskException("Meet error in task." + Thread.currentThread().getName());
/*                if(id==3){
                        throw new TaskException("Meet error in task." + Thread.currentThread().getName());
                }*/
                //һ��ģ���ʱ�Ĳ��� 
                for (int i = 999999; i > 0; i--) ;
                return "result>>>>>>>....has called ,result,return...:" + id + "    " + Thread.currentThread().getName();
        } 
}

class TaskException extends Exception {
        public TaskException(String message) {
                super(message);
        }
}