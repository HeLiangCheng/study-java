package com.hlc.study.java.concurrent.day08.disruptor.workpool;

import com.lmax.disruptor.*;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

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

        int THREAD_NUMBERS = 10;

        //事件创建工厂
        TradeEventFactory factory = new TradeEventFactory();

        //创建bufferSzie,也就是RingBuffer的大小，必须是2的n次方
        int ringbuffersize = 1024 * 1024;

          /*
          createSingleProducer  创建一个单生产者的RingBuffer
              第一个参数叫EventFactory，从名字上理解就是"事件工厂"，其实它的职责就是产生数据填充RingBuffer的区块。
              第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
              第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
         */
        final RingBuffer<Trade> rb = RingBuffer.createSingleProducer(factory, ringbuffersize, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建SequenceBarrier,用于协调生产者和消费者速度
        SequenceBarrier sequenceBarrier = rb.newBarrier();

        //workpool
        WorkHandler<Trade> workhandler = new TradeHandler();

        WorkerPool<Trade> workerpool = new WorkerPool<Trade>(rb, sequenceBarrier, new IgnoreExceptionHandler(), workhandler);

        workerpool.start(executors);

        //生产数据
        for(int i=0;i<10;i++){
            long sequence=rb.next();
            Trade trade=rb.get(sequence);
            trade.setId(UUID.randomUUID().toString());
            trade.setPrice(new Random().nextDouble()*1000);
            rb.publish(sequence);
        }

        workerpool.halt();
        executors.shutdown();//终止线程
    }
}
