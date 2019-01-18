package com.hlc.study.java.netty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/18 15:00
    *@Version 1.0
**/

class Server2Thread implements Runnable {

    private int port;

    public Server2Thread(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hello netty \r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new OioEventLoopGroup();

        try {
            //创建ServerBootstrap，并配置netty参数
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture future = b.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

public class Server2 {
    public static void main(String[] args) {
        System.out.println("Server 已经启动 .....");
        new Thread(new Server2Thread(8894)).start();
    }
}
