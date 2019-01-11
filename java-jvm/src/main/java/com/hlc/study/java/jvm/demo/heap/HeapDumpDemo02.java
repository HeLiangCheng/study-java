package com.hlc.study.java.jvm.demo.heap;
/**
    *@ClassName HeapDumpDemo02
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 9:08
    *@Version 1.0
**/

public class HeapDumpDemo02 {
    /*
循环10次，每次分配1M，新创建大对象直接放入到老年代

-Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails
        堆大小20m,年轻代为1M， Eden/s1/s2 = 0.8/0.1/0.1 不能创建对象，所以对象全部创建到老年代

-Xmx20m -Xms20m -Xmn15m -XX:+PrintGCDetails
       堆大小20m,年轻代为15M， Eden/s1/s2 = 12/1.5/1.5 可以在Eden区域创建对象，不会使用老年代

 -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
        幸存区： E/S1/S2=2:1:1  E=5m   S1=2.5m  S2=2.5  当对象创建先进入Eden区域，当Eden区域装满,将对象移动S1区域，并且启动GC回收Eden区域，再次到Eden区域创建对象，当Eden区域放满，S1也放满，则先执行GC回收在放入对象

-Xms20m -Xmx20m -XX:NewRatio=1 -XX:SurvivorRatio=3 -XX:+PrintGCDetails
        年轻代和年老代各占一半（10m），幸存区为3：1：1（6m:2m,2m)
*/
    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);
        System.out.println("开始分配");
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            System.out.println("第"+i+"次分配");
            b = new byte[1 * 1024 * 1024];
            Thread.sleep(1000);
            System.out.println("分配完成");
        }
    }
}
