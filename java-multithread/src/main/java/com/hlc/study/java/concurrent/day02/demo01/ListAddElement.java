package com.hlc.study.java.concurrent.day02.demo01;

import java.util.LinkedList;
import java.util.Random;

/**
    *@ClassName ListAddElement
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:07
    *@Version 1.0

    wait notfiy 方法，
    wait释放锁，
    notfiy不释放锁
    实例化出来一个 lock,当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
    缺点：响应不实时

后期使用CountDownLatch：来解决实时问题CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。

 **/

public class ListAddElement {
    //容量20
    private static volatile LinkedList<String> list = new LinkedList<String>();

    public void add() {
        if (list != null) {
            String item = new Random(100).nextInt() + "";
            System.out.println("已经生产一个数据：" + item);
            list.add(item);
        }
    }

    public String get(){
        String item = list.poll();
        System.out.println("已经消费一个数据：" + item);
        return item;
    }

    public int getsize(){return list.size();}

    public static void main(String[] args){
        final ListAddElement  element =new ListAddElement();
        final Object lock=new Object();

        //生产者
        Thread t1=new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (lock) {
                      for(int i=0;i<50;i++) {
                            Thread.sleep(100);
                            if (element.getsize() > 10) {
                                System.out.println("-----------通知消费-----------------");
                                lock.notify();
                            }else {
                                element.add();
                            }
                        }
                    }
                } catch (Exception ex) {

                }
            }
        });

        //消费者
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (lock) {
                        while (true) {
                            if (element.getsize() <= 0) {
                                lock.wait();
                            }else {
                                element.get();
                            }
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();


    }

}
