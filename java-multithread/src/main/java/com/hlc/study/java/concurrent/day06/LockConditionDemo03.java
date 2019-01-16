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
  ReentrantLock 是可重入锁
 */
public class LockConditionDemo03 {

    private Lock lock = new ReentrantLock();

    public void method01() {
        try {
            lock.lock();
            System.out.println("开始执行method01");
            Thread.sleep(2000);
            method02();
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
            System.out.println("method02 执行完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //创建线程
    public static void main(String[] args) {
        final LockConditionDemo03 demo = new LockConditionDemo03();
        demo.method01();
    }

}
