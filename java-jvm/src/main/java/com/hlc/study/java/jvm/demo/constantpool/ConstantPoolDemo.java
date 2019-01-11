package com.hlc.study.java.jvm.demo.constantpool;
/**
    *@ClassName ConstantPoolDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/10 15:42
    *@Version 1.0
**/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  运行池常量池 -XX:PermSize  -XX:MaxPermSize 指定大小
 *  VM ags      -XX:PermSize=10m  -XX:MaxPermSize=10m
 *
 *  经测算，JDK1.7之后已经将字符串常量已入到Heap区域，而方法区只保留起实例的引用， JDK1.6是将字符串常量保存到PSPermGen
 *  所有在JDK1.7之后使用String.intern() 对象还是存放到Heap中
 *
 */
public class ConstantPoolDemo {
   private static List<String> list = new ArrayList<String>();
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            //intern()方法到运行时常量池中查看该字符串是否存在，如果存在则返回该字符串，如果不存在就将数据添加到常量池中
            String item = new String(new Date().toString()+(i++)).intern();
            list.add(item);
            System.out.println("当前 i= " + i);
        }
    }
}
