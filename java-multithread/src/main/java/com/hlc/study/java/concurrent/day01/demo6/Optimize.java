package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName Optimize
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 13:24
    *@Version 1.0

 减少锁粒度，提高性能

**/

public class Optimize {

    public synchronized void print1() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行，正在执行一个较长时间的业务操作，其内容不需要同步");
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
    }

    public void print2(){
        synchronized(this) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行，正在执行一个较长时间的业务操作，其内容不需要同步");
        }
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        final Optimize demo = new Optimize();

        long nowtime = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    demo.print2();
                    //demo.print1();
                }
            });
            t1.start();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("耗时：" + (endtime - nowtime));

    }

}
