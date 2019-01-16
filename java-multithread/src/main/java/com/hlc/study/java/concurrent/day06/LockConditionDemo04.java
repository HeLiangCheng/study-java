package com.hlc.study.java.concurrent.day06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    *@ClassName LockConditionDemo04
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 14:31
    *@Version 1.0

Condition类是Lock锁的等待和通知
Condition类似于wait()、notify()、notifyAll() 用户Lock锁的等待和唤醒
Condition一定是针对某一把锁

signal()
await()      当前线程等待
signalAll()  唤醒所有等待的线程

**/

public class LockConditionDemo04 {

    private Lock lock = new ReentrantLock();
    private Condition procondition = lock.newCondition();
    private Condition consumecondition = lock.newCondition();
    private ArrayBlockingQueue<String> blockQueue = new ArrayBlockingQueue<String>(5);

    public void producte_method() {
        try {
            lock.lock();
            for (int i = 0; i < 100; i++) {
                if (blockQueue.size() >= 5) {
                    procondition.await();
                }
                blockQueue.add("No:00" + i);
                System.out.println(Thread.currentThread().getName() + "生产者：No:00" + i);
                consumecondition.signal();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume_method() {
        try {
            lock.lock();
            while (true) {
                Thread.sleep(1000);
                String item = blockQueue.poll();
                if(item==null){
                    consumecondition.await();
                    procondition.signal();
                }else {
                    System.out.println(Thread.currentThread().getName() + "消费者已经消费：" + item);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final LockConditionDemo04 demo = new LockConditionDemo04();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                demo.consume_method();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                demo.producte_method();
            }
        });

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(t1);
        pool.submit(t2);

        pool.shutdown();
    }

}
