package com.hlc.study.java.concurrent.day08.disruptor.multiproducerconsumer;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
    *@ClassName Consumer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 11:17
    *@Version 1.0
**/

public class Consumer implements WorkHandler<Trade> {
    private static AtomicInteger count = new AtomicInteger(0);
    private String consumerId;

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public void onEvent(Trade trade) throws Exception {
        count.incrementAndGet();
        System.err.println("消费者：" + consumerId + " : 正在处理 [id=" + trade.getId() + ",name=" + trade.getName() + ",price=" + trade.getPrice());
    }

    public int getCount(){
        return count.get();
    }

}
