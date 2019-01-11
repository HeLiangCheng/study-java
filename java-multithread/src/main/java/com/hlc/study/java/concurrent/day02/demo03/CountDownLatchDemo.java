package com.hlc.study.java.concurrent.day02.demo03;

import java.util.concurrent.CountDownLatch;

/**
    *@ClassName CountDownLatchDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:59
    *@Version 1.0

CountDownLatch与CyclicBarrier有点儿相似。CyclicBarrier所描述的是“允许一组线程互相等待，直到到达某个公共屏障点，才会进行后续任务”，而CountDownLatch所描述的是”在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待“。

用给定的计数初始化 CountDownLatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。

CountDownLatch是通过一个计数器来实现的，当我们在new 一个CountDownLatch对象的时候需要带入该计数器值，该值就表示了线程的数量。每当一个线程完成自己的任务后，计数器的值就会减1。当计数器的值变为0时，就表示所有的线程均已经完成了任务，然后就可以恢复等待的线程继续执行了。

CountDownlatch与CyclicBarrier区别：
CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待
CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier

CountDownLatch内部通过共享锁实现。在创建CountDownLatch实例时，需要传递一个int型的参数：count，该参数为计数器的初始值，也可以理解为该共享锁可以获取的总次数。当某个线程调用await()方法，程序首先判断count的值是否为0，如果不会0的话则会一直等待直到为0为止。当其他线程调用countDown()方法时，则执行释放共享锁状态，使count值-1。当在创建CountDownLatch时初始化的count参数，必须要有count线程调用countDown方法才会使计数器count等于0，锁才会释放，前面等待的线程才会继续运行。注意CountDownLatch不能回滚重置。

**/

public class CountDownLatchDemo {

    private static CountDownLatch countdownlatch =new CountDownLatch(5);

    static class BossThread  implements Runnable{
        public void run() {
            System.out.println(Thread.currentThread().getName() + "老板已经到了，等待员工到齐之后再开会");
            try {
                //等待员工 count=0
                countdownlatch.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "人员就位，我们开始开会");
        }
    }

    static class WorkThread implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "员工到达会场");
            ///员工到达会议室 count - 1
            countdownlatch.countDown();
        }
    }

    public static void main(String[] args) {
        Thread b1 = new Thread(new BossThread(),"b-1");
        b1.start();

        for(int i=1;i<=5;i++) {
            Thread w1 = new Thread(new WorkThread(),"w-"+i);
            w1.start();
        }

    }
}
