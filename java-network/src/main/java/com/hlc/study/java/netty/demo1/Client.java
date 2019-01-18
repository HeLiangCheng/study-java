package com.hlc.study.java.netty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/18 15:13
    *@Version 1.0
**/

class ClientThread implements Runnable {

    private String address;
    private int port;

    public ClientThread(String address,int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        //1.创建工作线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2.创建Bootstrap对象并配置
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new DemoClientInitializer());

            //3.连接服务器
            Channel ch = bootstrap.connect(address, port).sync().channel();
            //4.控制台输入数据
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入：");
                String mgs = scanner.next();
                if (mgs.equals("exit")) {
                    break;
                }
                /*向服务器发送的文本信息中必须要是有"\r\n"作为结束符，之所以使用\r\n结束是因为
                  我们在handler中添加DelimiterBasedFrameDecoder 帧解码，该解码器会根据\n符号位分隔。所以每条消息的最后必须加上\n否则无法识别和解码
                 */
                ch.writeAndFlush(mgs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

public class Client{

    public static void main(String[] args){
        System.out.println("Server 已经启动 .....");
        new Thread(new ClientThread("127.0.0.1",8894)).start();
    }
}
