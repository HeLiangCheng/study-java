package com.hlc.study.java.jvm.demo.gc;
/**
    *@ClassName ReferenceCountDemo
    *@Description  引用计数法
    *@Author Liang
    *@Date 2019/1/10 16:58
    *@Version 1.0
**/
/*
  Java虚拟机已经回收该对象
 */
public class ReferenceCountDemo {

    private byte[] data = new byte[1024 * 1024];
    private ReferenceCountDemo reference;

    public static void main(String[] args) {
        ReferenceCountDemo a1 = new ReferenceCountDemo();
        ReferenceCountDemo b2 = new ReferenceCountDemo();

        //相互引用
        a1.reference = b2;
        b2.reference = a1;

        //设置为null
        a1 = null;
        b2 = null;

        //垃圾回收
        System.gc();
    }
}
