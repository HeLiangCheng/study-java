package com.hlc.study.java.concurrent.day02.demo03;
/**
    *@ClassName ThreadLocalDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:59
    *@Version 1.0
**/

public class ThreadLocalDemo {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        // 实现initialValue()
        public Integer initialValue() {
            return 0;
        }
    };

    public int nextSeq(){
        seqCount.set(seqCount.get() + 1);
        return seqCount.get();
    }

    private static class SeqThread extends Thread{
        private ThreadLocalDemo seqCount;
        SeqThread(ThreadLocalDemo seqCount){
            this.seqCount = seqCount;
        }

        public void run() {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }

    public static void main(String[] args){
        ThreadLocalDemo demo = new ThreadLocalDemo();

        SeqThread thread1 = new SeqThread(demo);
        SeqThread thread2 = new SeqThread(demo);
        SeqThread thread3 = new SeqThread(demo);
        SeqThread thread4 = new SeqThread(demo);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }



}
