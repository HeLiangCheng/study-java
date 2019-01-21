package com.hlc.study.java.netty.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 10:01
    *@Version 1.0
**/

public class Server implements Runnable {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        NioEventLoopGroup workGroup = null;
        NioEventLoopGroup bossGroup = null;
        try {
            //1.创建两个工作线程组
            workGroup = new NioEventLoopGroup();
            bossGroup = new NioEventLoopGroup();
            //2.创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            //3.配置ServerBootstrap
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            //设置NIOServerSocketChannel的tcp参数，BACKLOG大小
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childHandler(new ServerHandlerInitializer());
            //绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (workGroup != null) {
                workGroup.shutdownGracefully();
            }
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8989);
        new Thread(server).start();
    }

}
















