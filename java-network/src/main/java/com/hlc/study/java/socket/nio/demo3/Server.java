package com.hlc.study.java.socket.nio.demo3;
/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/17 15:44
    *@Version 1.0
**/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
    NIO 通信的步骤
 */
public class Server implements Runnable{
    //1.创建多路复用器
    private Selector selector = null;
    //2.创建缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    private ServerSocketChannel ssc = null;

    public Server(int port) {
        try {
            //3. 打开多路复用器
            selector = Selector.open();
            //4.打开服务通道
            ssc = ServerSocketChannel.open();
            //5.设置服务器是否为阻塞
            ssc.configureBlocking(false);
            //绑定地址
            ssc.bind(new InetSocketAddress(port));
            //注册多路复用器到通道上
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start at port:" + port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        //接收数据
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey selectKey = keys.next();
                    keys.remove();
                    //判断选择的元素是否有效
                    if (!selectKey.isValid()) {
                        continue;
                    }
                    //如果为阻塞状态这添加到多路复用器
                    if (selectKey.isAcceptable()) {
                        this.accept(selectKey);
                    }
                    //如果为可读状态
                    if (selectKey.isReadable()) {
                        this.read(selectKey);
                    }
                    //如果为可写状态
                    if (selectKey.isWritable()) {
                        this.write(selectKey);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void read(SelectionKey selectKey) {
        try {
            readBuffer.clear();
            SocketChannel keyssc = (SocketChannel) selectKey.channel();
            int count = keyssc.read(readBuffer);
            //没有数据读取
            if (count == -1) {
                selectKey.channel().close();
                selectKey.cancel();
                return;
            }
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String str = new String(bytes);
            System.out.println("server端接收到客户端信息" + str);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void write(SelectionKey selectKey) {
        try {
            writeBuffer.clear();
            SocketChannel sc = (SocketChannel) selectKey.channel();
            sc.configureBlocking(false);
            String backmsg = "server: 已经收到消息";
            byte[] by = backmsg.getBytes("UTF-8");
            writeBuffer.flip();
            writeBuffer.get(by);
            //写入管道
            sc.write(writeBuffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void accept(SelectionKey selectKey) {
        try {
            ServerSocketChannel keyssc = (ServerSocketChannel) selectKey.channel();
            SocketChannel channel = keyssc.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
