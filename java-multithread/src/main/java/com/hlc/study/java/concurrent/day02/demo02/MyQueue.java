package com.hlc.study.java.concurrent.day02.demo02;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
    *@ClassName MyQueue
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:43
    *@Version 1.0

使用wait和notfiy来模拟Queue
    Queue：是一个先进先出特性的对象列
    首先需要有一个集合盛装数据，指明集合的上限和下限
**/
public class MyQueue {

    private AtomicInteger listcapacity = new AtomicInteger(10);  //初始为10
    private  static  volatile LinkedList<String> list = new LinkedList<String>();
    private Object lock =new Object();

    public MyQueue(){

    }

    public MyQueue(int capacity) {
        if(capacity<Integer.MAX_VALUE) {
            listcapacity.set(capacity);
        }
    }

    public void put(String val) {
        try {
            synchronized (lock) {
                if (list.size() < listcapacity.intValue()) {
                    list.add(val);
                    listcapacity.incrementAndGet();
                    System.out.println("添加一条数据，唤醒消费者");
                    lock.notify();
                }
                lock.wait();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String take() {
        synchronized (lock){
            if(list.size()<=0){
                try {
                    lock.wait();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            String value = list.poll();
            listcapacity.decrementAndGet();
            System.out.println("消费一条数据，唤醒生产者");
            lock.notify();
            return value;
        }
    }


    public static void main(String[] args){
        final  MyQueue queue =new MyQueue(10);

        Thread t1=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    queue.put("t1-" + i);
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                while(true){
                    Object obj=queue.take();
                    System.out.println(" --> "+obj.toString());
                }
            }
        });

        t1.start();
        t2.start();

    }

}
