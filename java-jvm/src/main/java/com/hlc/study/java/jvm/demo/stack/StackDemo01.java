package com.hlc.study.java.jvm.demo.stack;
/**
    *@ClassName StackDemo01
    *@Description todo
    *@Author Liang
    *@Date 2019/1/10 10:27
    *@Version 1.0
**/

/**
 *  栈: 用于保存方法执行所需的数据,包括局部变量表，连接库，执行计数器，方法出口等,-Xss
 *
 *  VM args  -Xss128k
 */

public class StackDemo01 {
    private int length = 0;

    public static void main(String[] args) {
        StackDemo01 demo = new StackDemo01();
        demo.stack();
    }

    public void stack() {
        length++;
        System.out.println("当前栈深度" + length);
        stack();
    }
}
