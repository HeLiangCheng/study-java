package com.hlc.study.java.concurrent.day02.demo03;

import java.util.concurrent.CyclicBarrier;

/**
    *@ClassName CyclicBarrierDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:59
    *@Version 1.0

CyclicBarrier，一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
通俗点讲就是：让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。

**/
public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier =null;

    static class CyclicBarrierThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "进入了会议室...");
            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "发言");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("人员已经到齐，会议开始....");
            }
        });
        for(int i=1;i<=5;i++) {
            Thread thread = new Thread(new CyclicBarrierThread(),"t"+i);
            thread.start();
        }
    }

}
