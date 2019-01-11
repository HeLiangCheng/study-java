package com.hlc.study.java.jvm.demo.directmemory;
/**
    *@ClassName DirectMemoryDemo
    *@Description 直接内存操作
    *@Author Liang
    *@Date 2019/1/10 16:51
    *@Version 1.0
**/

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *  DirectMemory 容量可通过-XX:MaxDirectMemorySize 指定，如果不指定，则默认与 Java 堆的
 最大值（-Xmx 指定）一样。
 */
public class DirectMemoryDemo {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int count=0;
        while (true) {
            System.out.println(count++);
            unsafe.allocateMemory(_1MB);
        }
    }

}
