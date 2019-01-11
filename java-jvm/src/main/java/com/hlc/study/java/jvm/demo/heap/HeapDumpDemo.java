package com.hlc.study.java.jvm.demo.heap;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
    *@ClassName HeapDumpDemo
    *@Description 堆内存展示案例
    *@Author Liang
    *@Date 2019/1/10 10:03
    *@Version 1.0
**/

/**
 *  VM Args   -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -XX:+PrintGCDetails
 *
 *  对象优先分配在Eden
 *  大对象直接分配到老年代
 *  长期存活的对象进入老年代
 *
 */
public class HeapDumpDemo {

    public static void main(String[] args) {
        List<HeapOOm> list = new ArrayList<HeapOOm>();
        int num =0;
        while (true) {
            num++;
            System.out.println("分配"+num+"M空间");
            HeapOOm test = new HeapOOm();
            list.add(test);
        }
    }

    static  class HeapOOm {
        private byte[] by = new byte[1024 * 1024];  //1M
    }

}


