package com.hlc.study.java.concurrent.day01.demo7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
    *@ClassName AtomicUse
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 14:53
    *@Version 1.0

    多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性

**/

public class AtomicUse {

    private static AtomicInteger count=new AtomicInteger();

    public synchronized int addnum(){
        try{
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }


    public static void main(String[] args) {
        final AtomicUse atom=new AtomicUse();

        List<Thread> thlist=new ArrayList<Thread>();
        for(int i=0;i<100;i++){
            thlist.add(new Thread(new Runnable() {
                public void run() {
                    System.out.println(atom.addnum());
                }
            }));
        }

        //运行
        for(int i=0;i<thlist.size();i++){
            thlist.get(i).start();
        }
    }
}
