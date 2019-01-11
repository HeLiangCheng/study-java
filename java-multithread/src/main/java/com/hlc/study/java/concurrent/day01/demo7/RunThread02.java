package com.hlc.study.java.concurrent.day01.demo7;
/**
    *@ClassName RunThread
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:20
    *@Version 1.0

    volatile 关键字是让变量在多个线程中可见

**/

public class RunThread02 implements Runnable{


    //volatile
    private volatile int value=10;


    public void run() {
        System.out.println(Thread.currentThread().getName() + " 进入run方法....");
        System.out.println("value = " + value);
        while (value>3){
            value--;
        }
        try {
            Thread.sleep(4000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "线程停止 ");
    }


    public static void main(String[] args) {
        RunThread02 run=new RunThread02();

        Thread t1=new Thread(run,"t1");
        t1.start();

        Thread t2=new Thread(run,"t2");
        t2.start();
        System.out.println("程序退出！");
    }
}
