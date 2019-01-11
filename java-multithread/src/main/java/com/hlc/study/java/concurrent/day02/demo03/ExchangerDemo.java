package com.hlc.study.java.concurrent.day02.demo03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
    *@ClassName ExchangerDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:59
    *@Version 1.0

    Exchanger 可能在应用程序（比如遗传算法和管道设计）中很有用

**/

public class ExchangerDemo {

    static class Producer implements Runnable {

        //生产者、消费者交换的数据结构
        private List<String> buffer;
        //步生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        public void run() {
            for (int i = 0; i <= 3; i++) {
                System.out.println("生产者第" + i + "批数据");
                for (int j = 0; j < 5; j++) {
                    System.out.println("生产者存储到缓存数据" + i + "-" + j);
                    buffer.add(i + "-" + j);
                }
                System.out.println("生产者第" + i + "批数据装满，等待与消费者交换...");
                try {
                    exchanger.exchange(buffer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        //生产者、消费者交换的数据结构
        private List<String> buffer;
        //步生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        public void run() {
            for (int i = 0; i <= 3; i++) {
                try {
                    //调用exchange()与消费者进行数据交换
                    buffer = exchanger.exchange(buffer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("消费者第" + i + "次提取");
                for (int j = 1; j <= 5 ; j++) {
                    System.out.println("消费者 : " + buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

        Thread producer = new Thread(new Producer(buffer1, exchanger), "producer");
        Thread consumer = new Thread(new Consumer(buffer1, exchanger), "consumer");
        producer.start();
        consumer.start();
    }

}
