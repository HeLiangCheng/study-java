package com.hlc.study.java.concurrent.day01.demo5;

import java.util.Calendar;

/**
    *@ClassName MainSubLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 13:07
    *@Version 1.0

    子类调用父类的同步方法

    子类方法获取锁，调用父类同步方法时，可以直接调用不用再次获取锁

 **/

public class MainSubLock {

    static class  Main {
        public int count = 10;
        public synchronized void mainPrint(){
            System.out.println(Thread.currentThread().getName()+" Main ");
        }
    }

     static class Sub extends Main {
        public synchronized void subPrint() {
            try {
                while (count > 0) {
                    count--;
                    System.out.println(Thread.currentThread().getName()+"Sub print count=" + count);
                    Thread.sleep(2000);
                    mainPrint();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Sub subdemo1 = new Sub();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                subdemo1.subPrint();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                subdemo1.subPrint();
            }
        });

        t1.start();
        t2.start();
    }

}
