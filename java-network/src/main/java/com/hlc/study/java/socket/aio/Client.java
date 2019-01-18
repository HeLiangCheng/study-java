package com.hlc.study.java.socket.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
    *@ClassName Client
    *@Description todo
    *@Author Liang
    *@Date 2019/1/18 10:55
    *@Version 1.0
**/

public class Client {
    private static int PORT = 8848;
   public static void main(String[] args) {
       try {
           AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
           Future<Void> future = client.connect(new InetSocketAddress("127.0.0.1", PORT));
           future.get();
           ByteBuffer buffer = ByteBuffer.allocate(1024);
           client.read(buffer, null, new CompletionHandler<Integer, Object>() {

               @Override
               public void completed(Integer result, Object attachment) {
                   System.out.println("client received: " + new String(buffer.array()));
               }

               @Override
               public void failed(Throwable exc, Object attachment) {
                   exc.printStackTrace();
               }
           });
       } catch (Exception ex) {
           ex.printStackTrace();
       }

   }
}
