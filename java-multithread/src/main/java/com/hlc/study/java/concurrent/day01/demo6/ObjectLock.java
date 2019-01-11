package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName ObjectLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 13:32
    *@Version 1.0

使用synchronized代码块可以使用任意object 加锁，用法比较灵活

**/

public class ObjectLock {

    public void method1(){
        //对象锁
        synchronized (this) {
            try{
                System.out.println(Thread.currentThread().getName()+": do... method1 ");
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void method2(){
        //类锁
        synchronized (ObjectLock.class) {
            try{
                System.out.println(Thread.currentThread().getName()+": do...method2 ");
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //任何对象锁
    private Object lock = new Object();
    public void method3(){
        synchronized (lock) {
            try{
                System.out.println(Thread.currentThread().getName()+":  do ...method3");
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        final ObjectLock lock=new ObjectLock();

        Thread t1=new Thread(new Runnable() {
            public void run() {
                lock.method1();
            }
        },"t1");

        Thread t2=new Thread(new Runnable() {
            public void run() {
                lock.method2();
            }
        },"t2");

        Thread t3=new Thread(new Runnable() {
            public void run() {
                lock.method3();
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
