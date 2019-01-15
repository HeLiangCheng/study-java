package com.hlc.study.java.concurrent.day04.masterwork;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName Master
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 17:13
    *@Version 1.0
**/

public class Master {

    //放置所有的任务
    private ConcurrentLinkedQueue<Task> tasks;
    //放置所有的结果集
    private ConcurrentHashMap<Integer, Object> resultmap;
    //放置所有的worker
    private HashMap<String, Thread> workMap = new HashMap<String, Thread>();

    public Master(int capacity) {
        tasks = new ConcurrentLinkedQueue<Task>();
        resultmap = new ConcurrentHashMap<Integer, Object>();
        for (int i = 0; i < capacity; i++) {
            Thread t1 = new Thread(new Worker(tasks, resultmap), "thread-" + i);
            workMap.put("thread-" + i, t1);
        }
    }

    //任务添加
    public void addTask(Task task){
        tasks.add(task);
    }


    public void execute() {
        for (String thname : workMap.keySet()) {
            workMap.get(thname).start();
        }
    }

    public boolean isComplate() {
        for (Map.Entry<String, Thread> entry : workMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public double collectResult() {
        double sum = 0;
        for (Map.Entry<Integer, Object> item : resultmap.entrySet()) {
            sum += Double.parseDouble(item.getValue().toString());
        }
        return sum;
    }

}
