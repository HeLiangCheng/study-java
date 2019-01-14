package com.hlc.study.java.concurrent.day03.demo02.block;

import java.util.concurrent.SynchronousQueue;

/**
    *@ClassName SynchronousQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 14:24
    *@Version 1.0

    SynchronousQueue  数据缓冲的BlockingQueue，生产者线程对其的插入操作put必须等待消费者的移除操作take

**/

public class SynchronousQueueDemo {

    private SynchronousQueue<String> synQueue = new SynchronousQueue<String>();

    public static void main(String[] args) {
        final SynchronousQueueDemo demo = new SynchronousQueueDemo();

        //生产者
        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        System.out.println("生产者生产票务信息: No00" + i);
                        demo.synQueue.put("生产者生产票务信息: No00" + i);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        //消费者
        for(int i=1;i<=2;i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            String item = demo.synQueue.take();
                            System.out.println("消费票务信息: " + item);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
