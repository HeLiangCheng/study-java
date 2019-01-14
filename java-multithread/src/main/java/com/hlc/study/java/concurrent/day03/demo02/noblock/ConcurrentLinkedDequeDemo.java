package com.hlc.study.java.concurrent.day03.demo02.noblock;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
    *@ClassName ConcurrentLinkedDequeDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 15:47
    *@Version 1.0

ConcurrentLinkedDeque 非阻塞，双向链表结构的无界并发队列，采用了无锁算法，底层基于自旋+CAS的方式实现

**/

public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        final ConcurrentLinkedDeque<String>  deque = new ConcurrentLinkedDeque<String>();

        //头部新增
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"向队首追加元素 No00"+i);
                        deque.addFirst("No00" + i);
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
                        deque.addLast("No00" + i);
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
                        String str = deque.poll();
                        System.out.println("消费者消费：" + str);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
