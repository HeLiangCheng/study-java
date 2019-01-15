package com.hlc.study.java.concurrent.day08.disruptor.eventprocessor;

import com.hlc.study.java.concurrent.day08.disruptor.base.DataEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
    *@ClassName TradeHandler
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 17:52
    *@Version 1.0
**/

public class TradeHandler implements EventHandler<Trade>,WorkHandler<Trade> {

    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.out.println("处理订单 > " + trade.getId() + ":" + trade.getName() + ":" + trade.getPrice());
    }

    public void onEvent(Trade trade) throws Exception {
        this.onEvent(trade);
    }

}
