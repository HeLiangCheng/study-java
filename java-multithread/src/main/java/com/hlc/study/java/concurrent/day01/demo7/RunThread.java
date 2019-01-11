package com.hlc.study.java.concurrent.day01.demo7;
/**
    *@ClassName RunThread
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:20
    *@Version 1.0

    volatile 关键字是让变量在多个线程中可见

**/

public class RunThread  implements Runnable{


    //volatile
    private volatile  boolean isRunning=true;

    public void setIsRunning(boolean isRunning){
        this.isRunning=isRunning;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" 进入run方法....");
        int i=0;
        while(isRunning){
            i++;
        }
        System.out.println(Thread.currentThread().getName()+"线程停止 i="+i);
    }


    public static void main(String[] args) {
        RunThread run=new RunThread();

        Thread t1=new Thread(run,"t1");
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run.setIsRunning(false);
        System.out.println("设置isRunning=false");


        Thread t2=new Thread(run,"t2");
        t2.start();
        System.out.println("程序退出！");
    }
}
