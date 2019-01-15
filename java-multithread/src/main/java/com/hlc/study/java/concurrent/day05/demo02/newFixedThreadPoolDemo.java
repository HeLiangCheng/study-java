package com.hlc.study.java.concurrent.day05.demo02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *@ClassName newFixedThreadPoolDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 10:42
    *@Version 1.0
**/

public class newFixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(300);
                        System.out.println(Thread.currentThread().getName() + "开始执行....");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

    }
}

