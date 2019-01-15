package com.hlc.study.java.concurrent.day04.productconsume;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 9:36
    *@Version 1.0
**/

public class Main {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Message> queue = new ConcurrentLinkedQueue<Message>();

        //生产者
        Productor  productor1 = new Productor(queue);
        new Thread(productor1).start();

        Productor  productor2 = new Productor(queue);
        new Thread(productor2).start();

        //消费者
        for(int i=0;i<5;i++) {
            Consumer consumer = new Consumer(queue);
            new Thread(consumer).start();
        }

    }
}
