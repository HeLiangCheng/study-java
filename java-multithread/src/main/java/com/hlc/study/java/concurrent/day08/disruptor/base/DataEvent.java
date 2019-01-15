package com.hlc.study.java.concurrent.day08.disruptor.base;
/**
    *@ClassName Event
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 16:49
    *@Version 1.0
**/

//数据类
public class DataEvent {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
