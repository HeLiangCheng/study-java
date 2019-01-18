package com.hlc.study.java.socket.bio.io3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
    *@ClassName Server
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 14:43
    *@Version 1.0
**/

//服务端
public class Server {

    private static int maxPoolSize =20;
    private static int queueSize=200;
    private static int port = 8889;


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                50,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));

        System.out.println("启动服务端，监听数据.....");
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                socket = server.accept();
                executor.submit(new SocketHandler(socket));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            executor.shutdown();
        }
    }

}
