package com.hlc.study.java.concurrent.day01.demo3;

/**
    *@ClassName MyObject
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 10:44
    *@Version 1.0
**/

/*
    当多个线程同时调用对象的同步方法和非同步方法是，同步方法会被阻塞，异步方法者不会受影响

    t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
	t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
 */

public class MyObject {
    public synchronized void syncprint() {
        System.out.println(Thread.currentThread().getName()+"对象锁的同步方法调用...");
        try {
            Thread.sleep(5000);
        }catch (Exception ex){ ex.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+"对象锁的同步方法调用结束");
    }

    public void print2() {
        System.out.println(Thread.currentThread().getName()+"非同步方法调用");
    }

    public static void main(String[] args) {
        final MyObject obj = new MyObject();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                obj.syncprint();
            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                //obj.syncprint();
                obj.print2();
            }
        });

        t1.start();
        t2.start();
    }

}
