package com.hlc.study.java.concurrent.day01.demo1;

/**
    *@ClassName MyThread
    *@Description 多线程并发问题展示
    *@Author Liang
    *@Date 2019/1/11 9:52
    *@Version 1.0
**/

/**
 1. 线程安全
 线程安全概念：当多个线程访问某一个类（对象或方法）时，这个对象始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的。

 synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"

 */
public class MyThread extends Thread{

    private int count =10;

    @Override
    public synchronized void run() {
        while (count>0) {
            count--;
            System.out.println("当前线程" + Thread.currentThread().getName() + "当前count=" + count);
        }
    }

    public static void  main(String[] args) {
        MyThread myThread = new MyThread();

        Thread t1=new Thread(myThread,"thread-01");
        Thread t2=new Thread(myThread,"thread-02");
        Thread t3=new Thread(myThread,"thread-03");
        Thread t4=new Thread(myThread,"thread-04");

        //开始执行
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
