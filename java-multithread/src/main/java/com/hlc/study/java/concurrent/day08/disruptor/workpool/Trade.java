package com.hlc.study.java.concurrent.day08.disruptor.workpool;
/**
    *@ClassName Trade
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 17:48
    *@Version 1.0
**/
//订单数据
public class Trade {
    private String id;
    private String name;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
