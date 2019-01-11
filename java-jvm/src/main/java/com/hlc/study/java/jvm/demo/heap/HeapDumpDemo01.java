package com.hlc.study.java.jvm.demo.heap;

import java.util.Scanner;

/**
    *@ClassName HeapDumpDemo01
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 9:07
    *@Version 1.0
**/

public class HeapDumpDemo01 {
    public static void main(String args[]) {
        System.out.println("JVMDemo");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    //=====================内存初始信息=========================
                    System.out.print("Xmx=");
                    System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
                    System.out.print("free mem=");
                    System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
                    System.out.print("total mem=");
                    System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
                    break;

                case 2:
                    //====================第一次分配=========================
                    System.out.println("5MB array allocated");
                    byte[] b1 = new byte[5 * 1024 * 1024];
                    System.out.print("Xmx=");
                    System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
                    System.out.print("free mem=");
                    System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
                    System.out.print("total mem=");
                    System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
                    break;

                case 3:
                    //=====================第二次分配=========================
                    System.out.println("10MB array allocated");
                    byte[] b2 = new byte[10 * 1024 * 1024];
                    System.out.print("Xmx=");
                    System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
                    System.out.print("free mem=");
                    System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
                    System.out.print("total mem=");
                    System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
                    break;

                case 4:
                    //=====================OOM=========================
                    System.out.println("OOM!!!");
                    System.gc();
                    byte[] b3 = new byte[40 * 1024 * 1024];
                    break;

                case -1:
                    flag = false;
                    break;
            }
        }
    }
}
