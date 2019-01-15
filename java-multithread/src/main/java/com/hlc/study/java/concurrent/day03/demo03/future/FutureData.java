package com.hlc.study.java.concurrent.day03.demo03.future;

import java.awt.*;

/**
    *@ClassName FutureData
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 16:23
    *@Version 1.0

    虚拟的数据请求包
 **/

public class FutureData implements DataRequest{

    private FutureRealData realData;
    private  boolean isReady=false;

    public synchronized void setFutureData(FutureRealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }

    public synchronized String getRequest() {
        String result = null;
        while (!isReady) {
            try {
                wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        result = realData.getRequest();
        return result;
    }

}
