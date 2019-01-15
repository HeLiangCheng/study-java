package com.hlc.study.java.concurrent.day03.demo03.masterwork;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName Worker
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 17:13
    *@Version 1.0
**/
public class Worker implements Runnable {

    //当前存放任务集合
    private ConcurrentLinkedQueue<Task> tasks;
    //存放任务结果集合
    private ConcurrentHashMap<Integer,Object> resultmap;

    public Worker(ConcurrentLinkedQueue tasks,ConcurrentHashMap resultmap) {
        this.tasks = tasks;
        this.resultmap = resultmap;
    }

    public void run() {
        while (true) {
            Task task = tasks.poll();
            if (task == null) {
                break;
            }
            System.out.println("worker:" + Thread.currentThread().getName() + "开始处理任务：" + task.getId());
            resultmap.put(task.getId(), handle(task));
        }
    }

    private Object handle(Task task) {
        try {
            //模拟查询数据库等耗时操作
            Thread.sleep(2000);
            int rate = (int) Math.random() * 10;
            return task.getPrice() * 100 * rate;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
