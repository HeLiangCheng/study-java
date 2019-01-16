package com.hlc.study.java.concurrent.day08.disruptor.multihandler;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 17:53
    *@Version 1.0

    使用WorkerPool辅助创建消费者

**/

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //线程池大小
        int THREAD_NUMBERS = 10;
        //创建bufferSzie,也就是RingBuffer的大小，必须是2的n次方
        int bufferSize = 1024 * 1024;

        //事件创建工厂
        TradeEventFactory factory = new TradeEventFactory();

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建disruptor
        Disruptor<Trade> disruptor=new Disruptor<Trade>(factory, bufferSize, executors, ProducerType.MULTI,new YieldingWaitStrategy());

        /*
        //顺序执行
        EventHandlerGroup<Trade> handlergroup=disruptor.handleEventsWith(new TradeHandler01());
        handlergroup.then(new TradeHandler02());
        handlergroup.then(new TradeHandler03());
        */

        /*
        //菱形执行
        //使用disruptor创建消费者组C1,C2
        EventHandlerGroup<Trade> handlergroup=disruptor.handleEventsWith(new TradeHandler01(),new TradeHandler02());
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        handlergroup.then(new TradeHandler03());
        */

        //多边形执行
        TradeHandler01 h1 = new TradeHandler01();
        TradeHandler02 h2 = new TradeHandler02();
        TradeHandler03 h3 = new TradeHandler03();
        TradeHandler04 h4 = new TradeHandler04();
        TradeHandler05 h5 = new TradeHandler05();
        disruptor.handleEventsWith(h1, h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4, h5).handleEventsWith(h3);

        //启动disruptor开始执行
        disruptor.start();
        CountDownLatch latch=new CountDownLatch(1);

        //生产者准备
        executors.submit(new TradeProducer(disruptor,latch));
        //等待生产者执行完毕
        latch.await();

        disruptor.shutdown();
        executors.shutdown();//终止线程

    }
}
