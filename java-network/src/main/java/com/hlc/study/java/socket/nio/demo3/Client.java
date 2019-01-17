package com.hlc.study.java.socket.nio.demo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/17 15:44
    *@Version 1.0
**/

public class Client implements Runnable {

    private String ip;
    private int port;

    public Client(String ip,int port){
        this.ip=ip;
        this.port=port;
    }

    public void run() {
        //创建连接地址
        InetSocketAddress address = new InetSocketAddress(ip, port);
        //声明通道
        SocketChannel sc = null;
        //建立缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //打开通道
            sc = SocketChannel.open();
            //连接地址
            sc.connect(address);
            //接收用户输入
            while(true) {
                String info = Thread.currentThread().getName() + "向服务发送数据";
                byte[] by = info.getBytes(Charset.forName("UTF-8"));
                //将数据写入缓冲区
                buffer.put(by);
                //对缓冲区复位
                buffer.flip();
                //向管道中写出数据
                sc.write(buffer);
                //清空缓冲区
                buffer.clear();
                Thread.sleep(2000);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
