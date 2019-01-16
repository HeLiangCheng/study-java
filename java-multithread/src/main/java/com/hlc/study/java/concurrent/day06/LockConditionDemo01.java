package com.hlc.study.java.concurrent.day06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    *@ClassName LockConditionDemo01
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 15:55
    *@Version 1.0
**/

/*
  Condition类是Lock锁的等待和通知
  Condition类似于wait()、notify()、notifyAll() 用户Lock锁的等待和唤醒
  Condition一定是针对某一把锁
 */
public class LockConditionDemo01 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method01() {
        try {
            lock.lock();
            System.out.println("开始执行method01");
            Thread.sleep(2000);
            System.out.println("等待method02唤醒");
            condition.await();  //等待
            System.out.println("method01 计算结果...");
            Thread.sleep(800);
            System.out.println("method01 执行完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method02() {
        try {
            lock.lock();
            System.out.println("开始执行method02");
            Thread.sleep(2000);
            System.out.println("唤醒method01");
            condition.signal();  //唤醒等待的线程
            System.out.println("method02 执行完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //创建线程
    public static void main(String[] args) {
        final LockConditionDemo01 demo = new LockConditionDemo01();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Runnable() {
            public void run() {
                demo.method01();
            }
        });

        pool.submit(new Runnable() {
            public void run() {
                demo.method02();
            }
        });

        pool.shutdown();
    }

}
