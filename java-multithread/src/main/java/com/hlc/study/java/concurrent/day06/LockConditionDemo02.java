package com.hlc.study.java.concurrent.day06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    *@ClassName LockConditionDemo02
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 15:52
    *@Version 1.0
**/

class Task implements Runnable{

    private String name;
    private Lock lock;

    public Task(String name,Lock lock){
        this.name=name;
        this.lock=lock;
    }

    public void run() {
        try{
            //tryLock:尝试获取锁,如果没有获取到锁则不会执行
            //System.out.println("任务："+name+" 尝试获取锁:"+lock.tryLock());
            lock.lock();
            System.out.println("任务："+name+" 进入任务");
            Thread.sleep(1000);
            System.out.println("任务："+name+" 执行完成");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("任务："+name+" 释放锁");
            lock.unlock();
        }
    }

}

public class LockConditionDemo02 {

    public static void main(String[] args) {
		/*
		     ReentrantLock构造函数传入的true、false决定了该锁是否为公平锁
		     true为公平锁,公平锁会按照线程的添加顺序执行，会维护执行顺序，浪费资源
		     false为非公平锁
		 */

        ReentrantLock lock=new ReentrantLock(false);

        System.out.println("是否公平："+lock.isFair());

        Thread t1=new Thread(new Task("t1", lock));
        Thread t2=new Thread(new Task("t2", lock));
        Thread t3=new Thread(new Task("t3", lock));
        Thread t4=new Thread(new Task("t4", lock));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

		/*
		System.out.println("是否锁定："+lock.isLocked());
		System.out.println("t4线程是否在等待锁："+lock.hasQueuedThread(t4));

		ExecutorService service=Executors.newCachedThreadPool();
		service.submit(t1);
		service.submit(t2);
		service.submit(t3);
		service.submit(t4);
		service.shutdown();
		*/
    }

}
