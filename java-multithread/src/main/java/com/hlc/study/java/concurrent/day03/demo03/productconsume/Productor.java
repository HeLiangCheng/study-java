package com.hlc.study.java.concurrent.day03.demo03.productconsume;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName Productor
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 9:35
    *@Version 1.0
**/

public class Productor implements Runnable {

    private ConcurrentLinkedQueue<Message> queue = null;

    public Productor(ConcurrentLinkedQueue queue) {
        this.queue = queue;
    }

    public void run() {
        if (queue != null) {
            for (int i = 0; i < 10; i++) {
                Message message = new Message();
                message.setId(i);
                message.setName("msg_no:" + i);
                message.setBody("hello world");
                queue.add(message);
                System.out.println(Thread.currentThread().getName()+"新数据 》 " + message.getId() + ":" + message.getName() + " : " + message.getBody());
            }
        }
    }

}
