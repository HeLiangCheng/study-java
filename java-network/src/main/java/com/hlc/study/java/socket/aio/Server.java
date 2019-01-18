package com.hlc.study.java.socket.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/18 10:55
    *@Version 1.0
**/

public class Server {

    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetEncoder encoder = charset.newEncoder();
    private static int PORT = 8848;

    public static void main(String[] args) {
        try {
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(PORT));
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    server.accept(null, this);
                    try {
                        String date = new Date().toString();
                        ByteBuffer buffer = encoder.encode(CharBuffer.wrap(date + "\r\n"));
                        Future<Integer> f = result.write(buffer);
                        f.get();
                        System.out.println("sent to client: " + date);
                        result.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });

            group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
