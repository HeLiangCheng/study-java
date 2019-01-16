package com.hlc.study.java.socket.io2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 14:42
    *@Version 1.0
**/

public class Client {

    public static void main(String[] args) {
        System.out.println("启动服务端，监听数据.....");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8889);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String cmd="";
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入....");
                cmd = scanner.next();
                if(cmd.equals("exit")){
                    break;
                }
                writer.println(cmd);
                writer.flush();
                //获取服务端返回信息
                String result = reader.readLine();
                System.out.println("服务返回信息：" + result);
            }
            System.out.println("退出客户端");
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
