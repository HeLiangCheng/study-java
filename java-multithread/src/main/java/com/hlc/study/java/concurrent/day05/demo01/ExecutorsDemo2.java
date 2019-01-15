package com.hlc.study.java.concurrent.day05.demo01;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName ExecutorsDemo2
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 10:37
    *@Version 1.0
**/

public class ExecutorsDemo2 implements Runnable{

    public void run() {
        System.out.println("线程次创建");
    }

    public static void main(String[] args) {
        ExecutorsDemo2 command = new ExecutorsDemo2();
        ScheduledExecutorService service= Executors.newScheduledThreadPool(1);

		/*
			 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
			 Runnable command :  要执行的任务
			 long initialDelay : 首次执行的延迟时间
			 long delay :		  一次执行终止和下一次执行开始之间的延迟
			 TimeUnit unit :	 initialDelay 和 delay 参数的时间单位
		 */
        ScheduledFuture<?> scheduleTask = service.scheduleWithFixedDelay(command, 5, 2, TimeUnit.SECONDS);


		/*
		 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行， 接着在 initialDelay + 2 * period 后执行，依此类推
		  command - 要执行的任务
		  initialDelay - 首次执行的延迟时间
		  period - 连续执行之间的周期
		  unit - initialDelay 和 period 参数的时间单位
		 */
        ScheduledFuture<?> scheduleTask2 =service.scheduleAtFixedRate(command, 5, 2, TimeUnit.SECONDS);

    }
}
