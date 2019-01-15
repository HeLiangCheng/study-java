package com.hlc.study.java.concurrent.day05.demo02;

import javax.swing.text.AbstractDocument;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName newSingleThreadExecutorDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 10:49
    *@Version 1.0

isShutDown当调用shutdown()方法后返回为true。
isTerminated当调用shutdown()方法后，并且所有提交的任务完成后返回为true

**/

public class newSingleThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(300);
                        System.out.println(Runtime.getRuntime().availableProcessors() + "可用线程数量");
                        System.out.println(Thread.currentThread().getName() + "开始执行....");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        //关闭启动线程
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                break;
            }
        }
        /*
        try {
            // 等待子线程结束，再继续执行下面的代码
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            System.out.println("all thread complete");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        */

    }

}
