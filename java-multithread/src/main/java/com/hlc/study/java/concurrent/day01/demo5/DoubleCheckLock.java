package com.hlc.study.java.concurrent.day01.demo5;

import sun.plugin.com.DispatchClient;

/**
    *@ClassName DoubleCheckLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 11:05
    *@Version 1.0

 DCL: Double Check Lock
      单例模式懒汉式  -  懒汉式，在多线程情况下会出现线程不安全，添加synchronized同步
      但是synchronized添加到方法，容易造成性能损失，每一个线程在访问getInstance()方法时都需要获取锁，
      所以改进为DCL模块，先判断是否为null在对代码块加锁，这样提高线程访问性能，但是由于JVM重排序等原因
      有可能导致代码并非那样执行，就需要对DoubleCheckLock添加volatile让所有线程可见
 **/

public class DoubleCheckLock {
    private String name;

    private DoubleCheckLock() {
        name = "Singleton";
    }

    private volatile static DoubleCheckLock dcl = null;
    public static DoubleCheckLock getInstance() {
        //双层判断
        if (dcl == null) {
            synchronized (DoubleCheckLock.class) {
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (dcl == null) {
                    dcl = new DoubleCheckLock();
                }
            }
        }
        return dcl;
    }


    public static void main(String[] args) {
        for(int i=0;i<2000;i++) {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    DoubleCheckLock dcl = DoubleCheckLock.getInstance();
                    System.out.println(dcl);
                }
            });
            t1.start();
        }
    }

}
