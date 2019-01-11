package com.hlc.study.java.concurrent.day01.demo7;

import java.util.concurrent.atomic.AtomicInteger;

/**
    *@ClassName VolatileAtomic
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:34
    *@Version 1.0

    volatile关键字不具备synchronized关键字的原子性（同步），要使用原子性需要使用AtomicXXX原子类进行操作

**/

public class VolatileAtomic extends Thread{

    private static AtomicInteger count=new AtomicInteger(0);

    private static void addCount(){
        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName()+" 线程 ："+count);
    }

    @Override
    public void run() {
       addCount();
    }


    public static void main(String[] args) {
        VolatileAtomic[] array=new VolatileAtomic[100];
        for(int i=0;i<array.length;i++){
            array[i]=new VolatileAtomic();
        }
        //启动线程
        for(int i=0;i<array.length;i++){
            array[i].start();
        }

        try {
            Thread.sleep(5000);
        }catch (Exception ex){}

    }

}
