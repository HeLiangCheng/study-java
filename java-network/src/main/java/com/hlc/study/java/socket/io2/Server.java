package com.hlc.study.java.socket.io2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
        try {
            ServerSocket server = new ServerSocket(port);
            while(true) {
                Socket socket = server.accept();
                new Thread(new SocketHandler(socket)).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
