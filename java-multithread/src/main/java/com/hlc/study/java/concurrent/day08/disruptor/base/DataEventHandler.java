package com.hlc.study.java.concurrent.day08.disruptor.base;


import com.lmax.disruptor.EventHandler;

/**
    *@ClassName DataEventHandler
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 16:53
    *@Version 1.0
**/
//需要有一个监听事件类，用于处理数据（Event类）
public class DataEventHandler implements EventHandler<DataEvent> {

    public void onEvent(DataEvent dataEvent, long l, boolean b) throws Exception {
        System.out.println("处理数据： 》 "+dataEvent.getValue());
    }
}
