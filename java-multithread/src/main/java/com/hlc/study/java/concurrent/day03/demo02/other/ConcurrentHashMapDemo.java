package com.hlc.study.java.concurrent.day03.demo02.other;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
    *@ClassName ConcurrentHashMapDemo
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 15:48
    *@Version 1.0

ConcurrentHashMap
    支持并发的HashMap， Jdk1.7底层采用数组+链表；JDK1.8底层采用数组+链表/红黑树

**/

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        final ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<String, String>();



    }
}
