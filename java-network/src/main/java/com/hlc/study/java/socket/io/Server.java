package com.hlc.study.java.socket.io;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 14:43
    *@Version 1.0
**/

//服务端
public class Server {

    private static int port = 8889;

    public static void main(String[] args) {
        System.out.println("启动服务端，监听数据.....");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                //处理输入数据
                String line = reader.readLine();
                System.out.println("接收到client端传输的数据：" + line);
                //输出数据处理
                writer.println("收到");
                writer.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if(reader!=null){
                try {reader.close();}catch (Exception ex){ex.printStackTrace();}
            }
            if(writer!=null){
                try { writer.close();}catch (Exception ex){ex.printStackTrace();}
            }
        }
    }

}
