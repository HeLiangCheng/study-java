package com.hlc.study.java.socket.nio.demo3;
/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/17 16:53
    *@Version 1.0
**/

public class Main {

    public static void main(String[] args) {
        int port = 8848;
        System.out.println("启动服务器");
        new Thread(new Server(port)).start();

        //模拟多个客户端请求
        for(int i=0;i<2;i++) {
            new Thread(new Client("127.0.0.1", port), "No-00" + i).start();
        }
        try {
            Thread.sleep(20000);
        }catch (Exception ex){ex.printStackTrace();}


    }

}
