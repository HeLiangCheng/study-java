package com.hlc.study.java.concurrent.day03.demo03.productconsume;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName Consumer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 9:35
    *@Version 1.0
**/

public class Consumer implements Runnable {

    private ConcurrentLinkedQueue<Message> queue = null;

    public Consumer(ConcurrentLinkedQueue queue) {
        this.queue = queue;
    }


    public void run() {
        while (true) {
            if (queue != null) {
                Message msg = queue.poll();
                if (msg == null) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "已经消费数据 》 " + msg.getId() + ":" + msg.getName() + " : " + msg.getBody());
            }
        }
    }
}
