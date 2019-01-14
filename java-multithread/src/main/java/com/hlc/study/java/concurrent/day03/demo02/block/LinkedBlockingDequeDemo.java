package com.hlc.study.java.concurrent.day03.demo02.block;

import java.util.concurrent.LinkedBlockingDeque;

/**
    *@ClassName LinkedBlockingDequeDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 15:27
    *@Version 1.0

    LinkedBlockingDeque:    双端队列，没有实现读写分离，效率比LinkedBlockingQueue性能低，同一时刻只有有一个线程
    对齐进行操作。

**/

public class LinkedBlockingDequeDemo {

    public static void main(String[] args) {
        final LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<String>(20);

        //头部新增
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"向队首追加元素 No00"+i);
                        deque.putFirst("No00" + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

        //尾部新增
        new Thread(new Runnable() {
            public void run() {
                for (int i = 100; i > 50; i--) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"向队尾追加元素 No00"+i);
                        deque.putLast("No00" + i);
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
                        String str = deque.take();
                        System.out.println("消费者消费：" + str);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
