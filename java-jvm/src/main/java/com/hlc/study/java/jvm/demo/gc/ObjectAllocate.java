package com.hlc.study.java.jvm.demo.gc;
/**
    *@ClassName ObjectAllocate
    *@Description todo
    *@Author Liang
    *@Date 2019/1/10 17:10
    *@Version 1.0

    对象优先分配在Eden区域
    VM 参数   -Xms20M  -Xmx20M  -verbose:gc  -Xmn10M -XX:SurvivorRatio=8

    大对象直接分配到老年代
    长期存活对象将进入老年代

 **/

public class ObjectAllocate {

    public static int _1MB = 1024 * 1024;

    //对象优先分配在Eden区域
    public static void testAllocation() {
        byte[] allocation1 = new byte[1 * _1MB];
        byte[] allocation2 = new byte[1 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];
    }

    //大对象直接分配到老年代
    public static void testAllocation2() {
        byte[] allocation4 = new byte[8 * _1MB];
    }

    //长期存活对象将进入老年代, 何时进入老年代需要设定对象年龄 -XX:MaxTenuringThreshold=1
    public static void testAllocation3() {
        byte[] allocation0 = new byte[_1MB/4];
        byte[] allocation1 = new byte[4 * _1MB];
        byte[] allocation2 = new byte[4 * _1MB];
        byte[] allocation3 = new byte[4 * _1MB];
        allocation2=null;
        System.out.println("即将重新分配");
        allocation2=new byte[2*_1MB];
    }


    public static void main(String[] args) {
        //testAllocation();
        //testAllocation2();
        testAllocation3();
    }

}
