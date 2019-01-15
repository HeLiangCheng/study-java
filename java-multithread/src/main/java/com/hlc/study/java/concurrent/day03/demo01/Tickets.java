package com.hlc.study.java.concurrent.day03.demo01;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
    *@ClassName Tickets
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 9:23
    *@Version 1.0
**/

/*
   使用买票服务模拟Vector容器的线程同步问题
 */
public class Tickets {
     //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap
     //ArrayList<String> ticketlist = new ArrayList<String>();
     //Vector<String> ticketlist = new Vector<String>();
     private List<String> ticketlist =  Collections.synchronizedList(new ArrayList<String>());

    public static void main(String[] args) {


        final Tickets tickets = new Tickets();
        //初始化票池
        for(int i=0;i<100;i++) {
            tickets.ticketlist.add("No_00" + i);
            System.out.println("No_00" + i);
        }

        //使用线程买票
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (!tickets.ticketlist.isEmpty()) {
                            String item = tickets.ticketlist.remove(0);
                            System.out.println(Thread.currentThread().getName()+"消费火车票信息：" + item);
                        }
                    }
                }
            }).start();
        }

    }
}
