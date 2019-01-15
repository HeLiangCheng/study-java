package com.hlc.study.java.concurrent.day08.disruptor.base;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 16:56
    *@Version 1.0
**/

public class Main {

    public static void main(String[] args) {

        //事件创建工厂
        DataEventFactory factory = new DataEventFactory();

        //创建bufferSzie,也就是RingBuffer的大小，必须是2的n次方
        int ringbuffersize = 1024 * 1024;

        //线程池
        ExecutorService executor = Executors.newCachedThreadPool();

     	/*
		 * 创建Disruptor
		 * factory： 创建数据工作
		 * ringbuffersize： ringbuffer数据结构的大小
		 * executor：线程池，用于盛装和协调生产者消费者线程
		 * ProducerType: 生产者类型【SINGLE：单个生产者；MULTI多个生产者】
		 * WaitStrategy:  生产者和消费者协调策略
		 *      BlockingWaitStrategy    是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
		 *      SleepingWaitStrategy    性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但对生产者线程的影响最小，适合异步日志类似的场景
		 *      YieldingWaitStrategy    性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		 */
        Disruptor<DataEvent> disruptor = new Disruptor<DataEvent>(factory,
                ringbuffersize,
                executor,
                ProducerType.SINGLE,
                new YieldingWaitStrategy());

        //连接事件处理器
        disruptor.handleEventsWith(new DataEventHandler());

        //启动
        disruptor.start();

        //向disruptor中生产数据
        RingBuffer<DataEvent> rb=disruptor.getRingBuffer();

        //创建数据生产者
        DataEventProducer producer=new DataEventProducer(rb);
        //LongEventProducerWithTranslator producer=new LongEventProducerWithTranslator(rb);

        //生产数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for(int m = 0; m<100; m++){
            byteBuffer.putInt(0, m);
            producer.onData(byteBuffer);
        }

        //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        disruptor.shutdown();

        //关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown不会自动关闭；
        executor.shutdown();

    }

}
