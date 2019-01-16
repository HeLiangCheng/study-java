package com.hlc.study.java.concurrent.day06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    *@ClassName ReentrantLockDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 14:00
    *@Version 1.0
**/
/*

  重入锁： 它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放。这模仿了 synchronized 的语义；如果线程进入由线程已经拥有的监控器保护的 synchronized 块， 就允许线程继续进行，当线程退出第二个（或者后续） synchronized 块的时候，不释放锁，只有线程退出它进入的监控器保护的第一个 synchronized 块时，才释放锁

 */
public class ReentrantLockDemo {

    private static Lock lock = new ReentrantLock();  //其中包括公平锁或非公平锁，默认非公平锁NonfairSync

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "线程开始执行");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "线程开始执行");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        //t1.start();
        //t2.start();

        //使用线程池执行任务
        ExecutorService executser= Executors.newFixedThreadPool(2);
        executser.submit(t1);
        executser.submit(t2);
        executser.shutdown();

    }

}
