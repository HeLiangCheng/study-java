package com.hlc.study.java.socket.bio.io3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
    *@ClassName SocketHandler
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 15:43
    *@Version 1.0
**/

public class SocketHandler implements Runnable {

    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程处理请求");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //处理输入数据
            String line = reader.readLine();
            System.out.println("接收到client端传输的数据：" + line);
            //输出数据处理
            writer.println("收到");
            writer.flush();
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
