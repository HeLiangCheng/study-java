package com.hlc.study.java.concurrent.day08.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
    *@ClassName EventFactory
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 16:49
    *@Version 1.0
**/

//需要让disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象
public class DataEventFactory implements EventFactory<DataEvent> {

    public DataEvent newInstance() {
        return new DataEvent();
    }
}
