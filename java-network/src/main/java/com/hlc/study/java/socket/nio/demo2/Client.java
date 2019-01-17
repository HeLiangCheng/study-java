package com.hlc.study.java.socket.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/17 15:44
    *@Version 1.0
**/

public class Client {
    public static void main(String[] args) {
        int PORT = 8848;
        //创建连接地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",PORT);
        //声明通道
        SocketChannel sc = null;
        //建立缓冲区
        ByteBuffer buffer =ByteBuffer.allocate(1024);

        try {
            //打开通道
            sc = SocketChannel.open();
            //连接地址
            sc.connect(address);
            //轮询检测用户输入
            Scanner scanner = new Scanner(System.in);
            while(true){
                //接收用户输入
                byte[] by = new byte[1024];
                System.out.println("请输入信息...");
                System.in.read(by);
                //将数据写入缓冲区
                buffer.put(by);
                //对缓冲区复位
                buffer.flip();
                //向管道中写出数据
                sc.write(buffer);
                //清空缓冲区
                buffer.clear();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(sc!=null){
                try { sc.close(); }catch (Exception ex){ex.printStackTrace();}
            }
        }
    }

}
