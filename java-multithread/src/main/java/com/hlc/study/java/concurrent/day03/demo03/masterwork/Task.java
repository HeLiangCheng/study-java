package com.hlc.study.java.concurrent.day03.demo03.masterwork;

import java.util.Date;

/**
    *@ClassName Task
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 17:12
    *@Version 1.0
**/
//任务信息
public class Task {
    private int id;
    private String name;
    private double price;

    public Task(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
