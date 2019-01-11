package com.hlc.study.java.jvm.demo.stack;
/**
    *@ClassName StackDemo02
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 9:06
    *@Version 1.0
**/

/**
 虚拟机栈是Java线程方法调用的动态内存模型，每一个方法调用将创建一个栈帧，栈帧中包含了，局部变量表，动态链接，方法出口，操作数栈等信息。
 栈帧：
 局部变量表
 动态链接
 方法出口
 操作数栈
 如果栈内存不足将抛出：StackOverflowError  OutOfMemoryError
 调整： Xss    例如： -Xss128k

 实验结果
 -Xss128k        最大栈深度385
 -Xss500k        最大栈深度3557
 */
public class StackDemo02 {

    //记录执行深度
    public static int count=0;
    public static final int m = 1024*1024;

    //StackOverflow
    public static void recursion(long a,long b,long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }

    //OOM
    public static void recursion2() {
        byte[] array = new byte[1*m];
        count++;
        recursion2();
    }

    public static void main(String[] args) {
        try {
            //recursion(1, 2, 3);
            recursion2();
        }catch (Throwable  ex){
            System.out.println(count);
            ex.printStackTrace();
        }
    }

}
