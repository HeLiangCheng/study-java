package com.hlc.study.java.concurrent.day02.demo03;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
    *@ClassName SemaphoreDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 15:59
    *@Version 1.0
**/


/*
    停车场仅有5个停车位，一开始停车场没有车辆所有车位全部空着，然后先后到来三辆车，停车场车位够，安排进去停车，然后又来三辆，这个时候由于只有两个停车位，所有只能停两辆，其余一辆必须在外面候着，直到停车场有空车位，当然以后每来一辆都需要在外面候着。当停车场有车开出去，里面有空位了，则安排一辆车进去（至于是哪辆 要看选择的机制是公平还是非公平）。
从程序角度看，停车场就相当于信号量Semaphore，其中许可数为5，车辆就相对线程。当来一辆车时，许可数就会减 1 ，当停车场没有车位了（许可书 == 0 ），其他来的车辆需要在外面等候着。如果有一辆车开出停车场，许可数 + 1，然后放进来一辆车。
 */
public class SemaphoreDemo {

    static class Park {
        //停车场具有5个车位
        private Semaphore semaphore = new Semaphore(5);

        public void park(Car car) {
            try {
                semaphore.acquire();
                System.out.println(car.getCarName() + "进入停车场,开始停车");
                long time = (long) (Math.random() * 10);
                Thread.sleep(time);
                System.out.println(car.getCarName() + "出停车场，停车" + time + "分钟");
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                semaphore.release();
            }
        }

    }

    static class  Car extends Thread {
        private String carname;
        private Park parking;

        public String getCarName() {
            return carname;
        }

        public Car(String carname,Park parking) {
            this.carname = carname;
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park(this);
        }
    }


    public static void main(String[] args) {
        Park park = new Park();
        for (int i = 0; i < 10; i++) {
            Thread carthread = new Thread(new Car("car-" + i, park));
            carthread.start();
        }
    }


}
