package com.hlc.study.java.concurrent.day08.disruptor.multiproducerconsumer;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 11:17
    *@Version 1.0
**/

class IntEventExceptionHandler implements ExceptionHandler {
    public void handleEventException(Throwable ex, long sequence, Object event) {
    }

    public void handleOnStartException(Throwable ex) {
    }

    public void handleOnShutdownException(Throwable ex) {
    }
}

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //线程池大小
        int THREAD_NUMBERS = 10;
        //创建bufferSzie,也就是RingBuffer的大小，必须是2的n次方
        int bufferSize = 1024 * 1024;

        //事件创建工厂
        TradeFactory factory = new TradeFactory();

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建ringBuffer
        RingBuffer<Trade> ringBuffer = RingBuffer.create(ProducerType.MULTI, factory, bufferSize, new YieldingWaitStrategy());
        //序列协调者SequenceBarrier
        SequenceBarrier barriers = ringBuffer.newBarrier();

        //创建多个消费者
        Consumer[] consumers = new Consumer[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("c" + i);
        }
        //使用WorkerPool辅助创建消费者
        WorkerPool<Trade> workerPool = new WorkerPool<Trade>(ringBuffer, barriers, new IntEventExceptionHandler(), consumers);

        //多个消费者和生产者必须添加
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(executors);

        //开始构建生产者
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 10; j++) {
                        p.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("---------------开始生产-----------------");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("总数:" + consumers[0].getCount());
        workerPool.halt();
        executors.shutdown();//终止线程
    }
}
