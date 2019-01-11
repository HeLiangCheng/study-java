package com.hlc.study.java.concurrent.day01.demo7;
/**
    *@ClassName VolatileNoAtomic
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:29
    *@Version 1.0

  volatile 具有线程间可见性，但是不具有原子性
  原子性是指一个操作是不可中断的，要么全部执行成功要么全部执行失败，有着“同生共死”的感觉。

 使用volatile关键字修饰变量，执行结果小于预期100*100=10000，volatile具有可见性，线程间可见。而count++这并不是一个原子操作，包含了三个步骤：
        1.读取变量count的值；
        2.对count+1；
        3.将新值赋值给变量count。
 如果线程A读取count到工作内存后，其他线程对这个值已经做了自增操作后，那么线程A的这个值自然而然就是一个过期的值，

 **/

public class VolatileNoAtomic extends Thread{

    private static volatile int count=0;

    public static void addnum(){
        for(int i=0;i<100;i++){
            count++;
        }
        System.out.println(Thread.currentThread().getName()+" 线程 ："+count);
    }

    @Override
    public void run() {
        addnum();
    }


    public static void main(String[] args) {
        VolatileNoAtomic[] array=new VolatileNoAtomic[100];
        for(int i=0;i<array.length;i++){
            array[i]=new VolatileNoAtomic();
        }

        //启动线程
        for(int i=0;i<array.length;i++){
            array[i].start();
        }
    }

}
