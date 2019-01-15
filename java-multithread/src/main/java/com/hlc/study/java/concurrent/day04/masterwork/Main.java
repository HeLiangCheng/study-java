package com.hlc.study.java.concurrent.day04.masterwork;
/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 17:14
    *@Version 1.0

 改进方法：
    使用线程池优化线程创建


**/

public class Main {
    public static void main(String[] args) {
        Master master = new Master(5);

        //创建任务
        for (int i = 0; i < 100; i++) {
            Task t = new Task(i, "task-00" + i, i * Math.random() * 10);
            master.addTask(t);
        }

        //开始执行
        master.execute();

        //检查是否完成
        long start = System.currentTimeMillis();
        while(true){
            if(master.isComplate()){
                double info=master.collectResult();
                System.out.println("程序执行结果："+info);
                break;
            }
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("程序执行时间："+end);
    }
}
