package com.hlc.study.java.netty.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 10:01
    *@Version 1.0
**/

public class Client implements Runnable {

    private String ipAddress;
    private int port;

    public Client(String ipAddress,int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();
            Bootstrap strap = new Bootstrap();
            strap.group(group);
            strap.channel(NioSocketChannel.class);
            strap.option(ChannelOption.TCP_NODELAY, true);
            strap.handler(new ClientHandlerInitializer());
            //发起异步连接操作
            ChannelFuture future = strap.connect(ipAddress, port);
            //等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (group != null) {
                group.shutdownGracefully();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 8989);
        new Thread(client).start();
    }

}
