package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName DeadLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:07
    *@Version 1.0
**/

public class DeadLock {

    private Object obj = new Object();
    private String lock = "lock";

    public void print1() {
        System.out.println("开始执行print1");
        synchronized (obj) {
            System.out.println("print1 获得obj锁");
            try {Thread.sleep(200);}catch (Exception ex){ex.printStackTrace();}
            synchronized (lock) {
                System.out.println("print1 获得lock锁");
            }
        }
        System.out.println("print1执行完毕");
    }

    public void print2() {
        System.out.println("开始执行print2");
        synchronized (lock) {
            System.out.println("print2 获得lock锁");
            try {Thread.sleep(200);}catch (Exception ex){ex.printStackTrace();}
            synchronized (obj) {
                System.out.println("print2 获得obj锁");
            }
        }
        System.out.println("print2执行完毕");
    }


    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                deadLock.print1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                deadLock.print2();
            }
        });
        t1.start();
        t2.start();
    }

}
