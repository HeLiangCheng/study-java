package com.hlc.study.java.concurrent.day01.demo2;
/**
    *@ClassName MultiThread
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 10:08
    *@Version 1.0
**/

/*
Synchronized同步静态方法和非静态方法总结
     Synchronized同步静态方法（实际上是对该类对象加锁，俗称“类锁”）
        case1：当多个线程访问同一个对象实例     会产生互斥
        case2: 当多个线程访问不同的对象实例     会产生互斥

    Synchronized同步非静态方法（实际上是对调用该方法的对象加锁，俗称“对象锁”）
        case1：当多个线程访问同一个对象实例     会产生互斥
        case2: 当多个线程访问不同的对象实例     不会产生互斥

 */

public class MultiThread {

    private static int num = 0;

    //添加static与不添加static
    public synchronized void printnum(String flag) {
        try {
            if ("a".equals(flag)) {
                num = 100;
                System.out.println("when flag=a  set num =100");
                Thread.sleep(5000);
            } else if ("b".equals(flag)) {
                num = 200;
                System.out.println("when flag=b  set num =200");
            }
            System.out.println("now tag " + flag + ", num = " + num);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MultiThread mythread = new MultiThread();
        final MultiThread mythread2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                mythread.printnum("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                mythread2.printnum("b");
            }
        });

        t1.start();
        t2.start();

    }
}
