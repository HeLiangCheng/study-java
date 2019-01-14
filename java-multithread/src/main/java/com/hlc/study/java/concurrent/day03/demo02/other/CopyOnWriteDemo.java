package com.hlc.study.java.concurrent.day03.demo02.other;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
    *@ClassName CopyOnWriteDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 15:47
    *@Version 1.0

    COW容器： CopyOnWriteArrayList是对ArrayList的改进，支持多线程的读写，每次写入元素将新生成一个List，替换
 旧的List，适合读多写少

             CopyOnWriteArrayList是对Set的改进，支持多线程的读写，每次写入元素将新生成一个Set，替换
旧的Set，适合读多写少
**/

public class CopyOnWriteDemo {

    public static void main(String[] args) {
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        final CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();

        Thread t1=new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println("CopyOnWriteArrayList 添加前："+ Integer.toHexString(list.hashCode()));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    list.add(i+"");
                    System.out.println("CopyOnWriteArrayList 添加后："+ Integer.toHexString(list.hashCode()));

                }
            }
        });

        t1.start();

        Thread t2=new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println("CopyOnWriteArraySet 添加前："+ Integer.toHexString(set.hashCode()));
                    set.add(i+"");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("CopyOnWriteArraySet 添加后："+ Integer.toHexString(set.hashCode()));
                }
            }
        });

        t2.start();

    }
}
