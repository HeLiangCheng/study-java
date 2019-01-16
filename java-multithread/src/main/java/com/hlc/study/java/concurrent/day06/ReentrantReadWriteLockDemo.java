package com.hlc.study.java.concurrent.day06;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
    *@ClassName ReentrantReadWriteLockDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 14:10
    *@Version 1.0
**/

public class ReentrantReadWriteLockDemo {

    //读写锁
    private static ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = rwlock.readLock();     //写锁是排他锁
    private static ReentrantReadWriteLock.WriteLock writeLock = rwlock.writeLock();  //读锁是共享锁

    public void method_write() {
        try {
            writeLock.lock();
            System.err.println(Thread.currentThread().getName()+"--> 开始写入信息");
            Thread.sleep(2000);
            System.err.println(Thread.currentThread().getName()+"--> 写入信息完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public void method_read(){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"《《 开始读取信息");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"《《 读取完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        final ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        //读取线程
        for (int i = 0; i < 5; i++) {
            Thread writeThread = new Thread(new Runnable() {
                public void run() {
                    demo.method_write();
                }
            }, "write-thread-0" + i);
            pool.submit(writeThread);
        }
        //写入线程
        for (int i = 0; i < 20; i++) {
            Thread readThread = new Thread(new Runnable() {
                public void run() {
                    demo.method_read();
                }
            }, "read-thread-0" + i);
            pool.submit(readThread);
        }

        pool.shutdown();

    }

}
