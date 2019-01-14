package com.hlc.study.java.concurrent.day03.demo02.block;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
    *@ClassName PriorityBlockingQueueDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 14:37
    *@Version 1.0

PriorityBlockingQueue： 基于优先级的阻塞队列，优先级判断通过构造函数传入Comparator接口，内部采用公平锁，无界队列

**/

public class PriorityBlockingQueueDemo {

    static class  Student{
        private String username;
        private int age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private PriorityBlockingQueue<Student>  stuQueue = new PriorityBlockingQueue<Student>(5, new Comparator<Student>() {
        public int compare(Student stu1, Student stu2) {
            int age = stu1.getAge()-stu2.getAge();
            if(age == 0){
                return age;
            }else {
                return age > 0 ? 1 : -1;
            }
        }
    });

    public static void main(String[] args) {
        final PriorityBlockingQueueDemo demo = new PriorityBlockingQueueDemo();

        //生产数据
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Student stu = new Student();
                    stu.setUsername("No00" + i);
                    stu.setAge((int) (Math.random() * 10));
                    demo.stuQueue.put(stu);
                    System.out.println("新增学生信息：Student("+stu.getUsername()+" , "+stu.getAge()+")");
                }
            }
        }).start();

        //消费数据
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Student stu = demo.stuQueue.take();
                        System.out.println("消费数据 > " + stu.getUsername() + " : " + stu.getAge());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
