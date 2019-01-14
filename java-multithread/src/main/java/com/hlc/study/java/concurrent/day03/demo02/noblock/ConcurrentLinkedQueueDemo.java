package com.hlc.study.java.concurrent.day03.demo02.noblock;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName ConcurrentLinkedQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 15:46
    *@Version 1.0

ConcurrentLinkedQueue  基于链接节点的无界线程安全队列，采用先进先出的规则对节点进行排序，内部采用CAS+自旋锁

 **/

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

        //尾部新增
        new Thread(new Runnable() {
            public void run() {
                for (int i = 100; i > 50; i--) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"向队列追加元素 No00"+i);
                        queue.add("No00" + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

        //消费
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                        String str = queue.poll();
                        System.out.println("消费者消费：" + str);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
