package com.hlc.study.java.concurrent.day03.demo02.block;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName DelayQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 14:58
    *@Version 1.0

    DelayQueue : 带有延迟时间的Queue

    案例模拟上网缴费过程
 **/

public class DelayQueueDemo {

    //上网的学生
    static class Student implements Delayed {
        private int id;
        private String username;
        private long endTime;
        // 定义时间工具类
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        public Student(int id, String username, long endTime) {
            this.id = id;
            this.username = username;
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getDelay(TimeUnit unit) {
            return this.getEndTime() - System.currentTimeMillis();
        }

        public int compareTo(Delayed o) {
            Student wm = (Student) o;
            return (this.getEndTime() - wm.getEndTime()) > 0 ? 1 : 0;
        }
    }

    private DelayQueue<Student> delayQueue = new DelayQueue<Student>();

    public static void main(String[] args) {
        final DelayQueueDemo  demo = new DelayQueueDemo();
        //添加上网人员
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 1; i < 100; i++) {
                        int time = (int) (Math.random() * 10);
                        Student stud = new Student(i, "Studen_00" + i, time);
                        System.out.println(stud.getUsername() + "同学开始上机,购买的时长为，" + time);
                        demo.delayQueue.put(stud);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();

        //移除上网人员
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Student stu = demo.delayQueue.take();
                        System.out.println(stu.getUsername() + "购买的时长为" +stu.getEndTime()+"上网时间到,强制下线");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t2.start();

    }

}
