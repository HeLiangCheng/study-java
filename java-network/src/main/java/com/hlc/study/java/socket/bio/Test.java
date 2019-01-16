package com.hlc.study.java.socket.bio;

/**
    *@ClassName Test
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 16:17
    *@Version 1.0
**/

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Client(), "Client-" + i).start();
        }
    }
}
