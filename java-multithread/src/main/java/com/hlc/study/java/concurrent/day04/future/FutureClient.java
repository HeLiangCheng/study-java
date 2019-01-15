package com.hlc.study.java.concurrent.day04.future;
/**
    *@ClassName FutureClient
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 16:22
    *@Version 1.0

Future  客户端

 **/

public class FutureClient {

    public FutureData request(final String str) {
        //创建一个对象
        final FutureData data = new FutureData();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                FutureRealData realData = new FutureRealData(str);
                data.setFutureData(realData);
            }
        });
        t1.start();
        return data;
    }
}
