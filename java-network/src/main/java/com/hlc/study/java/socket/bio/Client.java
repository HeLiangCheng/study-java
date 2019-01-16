package com.hlc.study.java.socket.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 16:09
    *@Version 1.0
**/

public class Client implements  Runnable {
    public void run() {
        System.out.println("启动服务端，监听数据.....");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8889);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String cmd = "客户端：" + Thread.currentThread().getName() + "发送数据";
            writer.println(cmd);
            writer.flush();
            //获取服务端返回信息
            String result = reader.readLine();
            System.out.println(Thread.currentThread().getName() + "服务返回信息：" + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
