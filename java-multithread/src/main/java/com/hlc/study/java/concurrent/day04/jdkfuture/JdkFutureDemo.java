package com.hlc.study.java.concurrent.day04.jdkfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
    *@ClassName CallableDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 13:50
    *@Version 1.0
**/

class Task implements Callable<String> {

    private String query;

    public Task(String query) {
        this.query = query;
    }

    public String call() throws Exception {
        System.out.println("开始调用查询方法，等待返回数据");
        Thread.sleep(2000);
        System.out.println("查询数据库成功....");
        String reult = Thread.currentThread().getName() + " > select data 500 ";
        return reult;
    }

}

public class JdkFutureDemo {
    public static void main(String[] args){
        try {
            System.out.println("主线程开始");
            FutureTask<String> futuertask = new FutureTask<String>(new Task("Thread01"));
            FutureTask<String> futuertask2 = new FutureTask<String>(new Task("Thread02"));
            //线程池
            ExecutorService service = Executors.newFixedThreadPool(2);
            service.submit(futuertask);
            service.submit(futuertask2);
            service.shutdown();
            System.out.println("---获取返回的数据---");
            System.out.println("futuertask 结果" + futuertask.get());

            Thread.sleep(2000);

            System.out.println("futuertask2 结果" + futuertask2.get());
            System.out.println("---处理完毕---");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
