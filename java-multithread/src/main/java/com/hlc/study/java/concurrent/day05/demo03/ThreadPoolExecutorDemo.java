package com.hlc.study.java.concurrent.day05.demo03;

import java.sql.Time;
import java.util.concurrent.*;

/**
    *@ClassName ThreadPoolExecutorDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 11:14
    *@Version 1.0
**/

public class ThreadPoolExecutorDemo {

    /**
     public ThreadPoolExecutor(int corePoolSize,
     int maximumPoolSize,
     long keepAliveTime,
     TimeUnit unit,
     BlockingQueue<Runnable> workQueue,
     ThreadFactory threadFactory,
     RejectedExecutionHandler handler)

     参数：
     corePoolSize - 池中所保存的线程数，包括空闲线程。
     maximumPoolSize - 池中允许的最大线程数。
     keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     unit - keepAliveTime 参数的时间单位。
     workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
     threadFactory - 执行程序创建新线程时使用的工厂。
     handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。

     在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
     若大于corePoolSize，则会将任务加入队列，
     若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
     若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。

     拒绝策略：
        AbortPolicy(): 默认策略,直接抛出异常RejectedExecutionException
        CallerRunsPolicy()：只要线程池不关闭，该策略是直接在调用者线程中运行被丢弃的任务
        DiscardOldestPolicy():  丢弃最老的请求，尝试再次获取当前任务
        DiscardPolicy():    丢弃无法处理的任务，不给于任何处理
        自定义策略
     */

    static class MyAbortPolicy implements RejectedExecutionHandler {

        public MyAbortPolicy() {
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("自定义处理..");
            System.out.println("正在写入数据库，或者file log 中");
            System.out.println("当前被拒绝任务为：" + r.toString());
        }
    }


    public static void main(String[] arge) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new MyAbortPolicy()
        );

        for (int i = 0; i < 12; i++) {
            Task task = new Task(i, "Task-No00" + i);
            poolExecutor.execute(task);
        }

        poolExecutor.shutdown();
    }

}
