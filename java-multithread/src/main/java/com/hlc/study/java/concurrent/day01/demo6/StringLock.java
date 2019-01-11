package com.hlc.study.java.concurrent.day01.demo6;
/**
    *@ClassName StringLock
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 13:33
    *@Version 1.0

synchronized代码块对字符串的锁，注意String常量池的缓存功能
synchronized(String常量) 这种格式时，参数对象的内存地址只要相同，synchronized关键字的同步功能就会生效。

 String  a = "abc";     //JDK1.6 先在常量池中查找是否有"abc"对象，如果有则创建对象a并指向这个对象，如果没有则先在常量池中创建"abc"对象，然后创建对象a并指向这个对象,这样每次获取的地址不会改变

 String a = new String("abc"); //先在常量池中查找是否有"abc"对象，如果有则把常量池中的对象复制一份，然后创建对象a并指向复制出来的这个对象，如果没有则先在常量池中创建"abc"对象，然后复制一份，然后创建对象a并指向复制出来的这个对象。

 **/
public class StringLock {

    public void method(){
        //synchronized("字符串常量")
        //new String("字符串常量")
        synchronized(new String("字符串常量")){
            while(true){
                try{
                    System.out.println("当前线程："+Thread.currentThread().getName()+"开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程："+Thread.currentThread().getName()+"结束");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        final StringLock strlock=new StringLock();

        Thread t1=new Thread(new Runnable() {
            public void run() {
                strlock.method();
            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                strlock.method();
            }
        });

        t1.start();
        t2.start();

        String abc = "abc";
        String abc2= new String("abc");
        System.out.println(abc==abc2);
        String abc3= new String("abc");
        System.out.println(abc2==abc3);
    }
}
