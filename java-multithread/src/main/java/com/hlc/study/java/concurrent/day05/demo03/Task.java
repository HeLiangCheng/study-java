package com.hlc.study.java.concurrent.day05.demo03;
/**
    *@ClassName Task
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 11:22
    *@Version 1.0
**/

public class Task implements Runnable{
    private int id;
    private String name;

    public Task(int id,String name) {
        this.id = id;
        this.name = name;
        System.out.println("创建新线程 》" + id + " : " + name);
    }

    public void run() {
        try {
            Thread.sleep(800);
            System.out.println("线程池 [" + id + " : " + name + "] 已经被消费");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
}
