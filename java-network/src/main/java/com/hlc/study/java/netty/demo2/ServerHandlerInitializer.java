package com.hlc.study.java.netty.demo2;


import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
    *@ClassName ServerHandlerInitializer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 10:12
    *@Version 1.0
    被sharable注解标记过的实例都会存入当前加载线程的threadlocal里面
 **/
@Sharable
class ServerMessageReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); //发生异常，关闭链路
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageInfoReq req = (MessageInfoReq) msg;
        if ("message".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscrib req : [" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getReqid()));
        }
    }

    private MessageInfoResp resp(int subReqID) {
        MessageInfoResp resp = new MessageInfoResp();
        resp.setResqid(subReqID);
        resp.setRespCode(0);
        resp.setMessage("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }

}

public class ServerHandlerInitializer  extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        channel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        channel.pipeline().addLast(new ServerMessageReqHandler());
    }

}
