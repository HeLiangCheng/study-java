package com.hlc.study.java.concurrent.day05.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *@ClassName ExecutorsDemo1
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 10:33
    *@Version 1.0
**/

public class ExecutorsDemo1 {
    public static void main(String[] args) {

       /*
		  创建具有固定数量的线程，数量始终不变。
		  当有任务提交时，如果线程池中有空闲线程则立即执行任务，
		  如果没有则被暂缓到一个等待的任务队列中
		 */
        ExecutorService excutorser1=Executors.newFixedThreadPool(10);


		/*
		   创建一个单线程的线程池，
		   当有任务提交时，如果线程空闲则执行；如果线程不空闲则加入到等待的缓存队列中
		 */
        Executors.newSingleThreadExecutor();


		/*
		   返回一个可以根据当前实际情况调整线程个数的线程池，不设置最大的线程数。
		  如果空闲的线程执行任务，并且没有新任务提交则不创建新的线程，
		  空闲线程如果没有任务执行则会在60秒之后自动回收
		 */
        Executors.newCachedThreadPool();


		/*
		  返回一个可以指定数量的线程池
		 */
        Executors.newScheduledThreadPool(10);

    }
}
