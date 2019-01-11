package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName ModifyLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 13:52
    *@Version 1.0

同一对象属性的修改不会影响锁的情况,只要对象的引用不改变就不会发生锁的改变

**/

public class ModifyLock {
    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //synchronized
    public void changeAttributte(String name,int age,ModifyLock lock) {
        try {
            synchronized (lock) {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + " 开始");
                this.setName(name);
                this.setAge(age);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + " 修改对象内容为： " + this.getName() + ", " + this.getAge());
                Thread.sleep(2000);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + " 结束");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        final ModifyLock mlock=new ModifyLock();

        Thread t1=new Thread(new Runnable() {
            public void run() {
                mlock.changeAttributte("admin", 20,mlock);
            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                mlock.changeAttributte("jim", 30,mlock);
            }
        });

        t1.start();
        try{
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        t2.start();
    }


}
