package com.hlc.study.java.netty.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
    *@ClassName ClientHandlerInitializer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 10:12
    *@Version 1.0
**/

class ClientMessageRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private MessageInfoReq subReq(int i) {
        MessageInfoReq req = new MessageInfoReq();
        req.setReqid(i);
        req.setUserName("message");
        req.setAddress("sichuang mianyann");
        req.setPhoneNumber("151988432332");
        req.setProductName("Netty Book For Marshalling");
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}

public class ClientHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        channel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        channel.pipeline().addLast(new ClientMessageRespHandler());
    }
}
