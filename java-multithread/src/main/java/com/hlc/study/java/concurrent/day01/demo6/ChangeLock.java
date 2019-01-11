package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName ChangeLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:04
    *@Version 1.0

    lock锁的引用发生改变，锁就发生了改变

**/

public class ChangeLock {

    private String lock = "a_lock";

    public void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                lock = "b_lock";
                Thread.sleep(5000);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ChangeLock chang = new ChangeLock();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                chang.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                chang.method();
            }
        }, "t2");


        t1.start();
        t2.start();
    }

}
