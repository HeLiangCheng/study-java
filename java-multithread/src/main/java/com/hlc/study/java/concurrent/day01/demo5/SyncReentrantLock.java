package com.hlc.study.java.concurrent.day01.demo5;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.Scanner;

/**
    *@ClassName ReentrantLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 12:46
    *@Version 1.0

    可重入锁:  当线程t1执行print()方法获取到锁之后，执行print2()方法就不需要在获取锁了

 **/

public class SyncReentrantLock {

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + "print");
        print2();
    }

    public synchronized void print(int num) {
        System.out.println(Thread.currentThread().getName() + "reslut = " + num);
    }

    public synchronized void print2() {
        System.out.println(Thread.currentThread().getName() + "print2");
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //如果抛出异常，自动释放锁
    public synchronized void dividnum() {
        while (true) {
            Scanner can = new Scanner(System.in);
            int num1 = can.nextInt();
            int num2 = can.nextInt();
            int num = num1 / num2;
            print(num);
        }
    }


    public static void main(String[] args) {
        final SyncReentrantLock reentrantLock = new SyncReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                //reentrantLock.print();
                reentrantLock.dividnum();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                //reentrantLock.print2();
                reentrantLock.dividnum();
            }
        });

        t1.start();
        t2.start();
    }

}
