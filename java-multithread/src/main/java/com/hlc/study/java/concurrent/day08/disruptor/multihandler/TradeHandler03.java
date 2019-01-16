package com.hlc.study.java.concurrent.day08.disruptor.multihandler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
    *@ClassName TradeHandler01
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 17:52
    *@Version 1.0
**/

public class TradeHandler03 implements EventHandler<Trade>,WorkHandler<Trade> {

    public void onEvent(Trade trade, long l, boolean b) throws Exception {
     this.onEvent(trade);
    }


    public void onEvent(Trade trade) throws Exception {
        System.out.println("Handler03 > " + trade.getId() + ":" + trade.getName() + ":" + trade.getPrice());
    }
}
