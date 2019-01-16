package com.hlc.study.java.concurrent.day08.disruptor.multihandler;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
    *@ClassName TradeProducer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 11:00
    *@Version 1.0
**/

class TradeEventTranslator implements EventTranslator<Trade> {
    public void translateTo(Trade trade, long l) {
        trade.setId(UUID.randomUUID().toString());
        trade.setPrice(new Random().nextDouble()*1000);
    }
}

public class TradeProducer implements Runnable{

    Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int LOOP=1;//模拟百万次交易的发生


    public TradeProducer(Disruptor<Trade> disruptor,CountDownLatch latch){
        this.disruptor=disruptor;
        this.latch=latch;
    }

    public void run() {
        TradeEventTranslator translator=new TradeEventTranslator();
        for(int i=0;i<LOOP;i++){
            disruptor.publishEvent(translator);
        }
        latch.countDown();
    }

}
