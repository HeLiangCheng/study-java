package com.hlc.study.java.concurrent.day03.demo02.block;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName LinkedBlockingQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 11:29
    *@Version 1.0

LinkedBlockingQueue: 基于链表，线程安全，阻塞队列，无界队列，内部维护一个链表缓冲区，内部实现读写锁分离，提高效率

**/

public class LinkedBlockingQueueDemo {

    private LinkedBlockingQueue<String> blockingQueue =new LinkedBlockingQueue<String>();

    public static void main(String[] args){
        final LinkedBlockingQueueDemo queueDemo = new LinkedBlockingQueueDemo();

        //生产者
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        if (i % 2 == 0) {
                            Thread.sleep(200);
                        }
                        queueDemo.blockingQueue.put("No00" + i);
                        System.out.println(Thread.currentThread().getName()+"生产者已经生产：No00" + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t1.start();

        //消费者
        for(int i=0;i<3;i++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            String item = queueDemo.blockingQueue.poll(2, TimeUnit.SECONDS);
                            System.out.println(Thread.currentThread().getName()+"消费者已经消费：" + item);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }

    }


}
