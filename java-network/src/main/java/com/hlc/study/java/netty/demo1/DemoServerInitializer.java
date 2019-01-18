package com.hlc.study.java.netty.demo1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;


/**
    *@ClassName DemoServerInitializer
    *@Description 实现一个能够像服务端发送文字的功能。服务端假如可以最好还能返回点消息给客户端，然客户端去显示。
    *@Author Liang
    *@Date 2019/1/18 15:22
    *@Version 1.0
**/

class DemoServerHandler extends SimpleChannelInboundHandler<String>{

    //收到消息打印
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("remote: " + ctx.channel().remoteAddress() + " Say:" + msg);
        //返回客户端消息
        ctx.writeAndFlush("receive your message !\n");
    }

    //覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        ctx.writeAndFlush("Welcom to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        super.channelActive(ctx);
    }

}

public class DemoServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();  //获取Channel Pipeline
        //以\n为结尾分隔符，解码器
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //字符串解码和编码
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        //自己的逻辑Handler
        pipeline.addLast("handler",new DemoServerHandler());
    }
}
