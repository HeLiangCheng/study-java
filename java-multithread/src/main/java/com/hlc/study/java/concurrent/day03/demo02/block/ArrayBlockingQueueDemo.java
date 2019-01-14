package com.hlc.study.java.concurrent.day03.demo02.block;
/**
    *@ClassName ArrayBlockingQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 10:49
    *@Version 1.0
**/


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue:  线程安全，基于数组实现，有界，阻塞队列，内部没有实现读写分离，生产者消费者不能并行
 *
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        final ArrayBlockingQueue<String>  ticktesqueue = new ArrayBlockingQueue<String>(10);

        //生产者线程
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(Thread.currentThread().getName()+"生产者已经生产： No00" + i);
                        ticktesqueue.put("No00" + i);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();

        //消费者线程
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        String item = ticktesqueue.poll(5, TimeUnit.SECONDS);
                        System.out.println(Thread.currentThread().getName()+"消费者已经消费："+item);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t2.start();

    }
}
