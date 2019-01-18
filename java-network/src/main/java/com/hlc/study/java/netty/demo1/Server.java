package com.hlc.study.java.netty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/18 15:00
    *@Version 1.0
**/

class ServerThread  implements Runnable {

    private int port;

    public ServerThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workBroup = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap并配置信息
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workBroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new DemoServerInitializer());

            //监听服务器绑定端口
            ChannelFuture future = b.bind(port).sync();
            //监听服务器关闭
            future.channel().closeFuture().sync();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workBroup.shutdownGracefully();
        }
    }

}

public class Server{
    public static void main(String[] args){
        System.out.println("Server 已经启动 .....");
        new Thread(new ServerThread(8894)).start();
    }

}
