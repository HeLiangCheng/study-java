package com.hlc.study.java.concurrent.day03.demo03.future;
/**
    *@ClassName FutureRealData
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 16:23
    *@Version 1.0

    真实的数据请求包

**/

public class FutureRealData implements DataRequest {

    private String result;

    public FutureRealData(final String query) {
        try {
            System.out.println("执行一个耗时较长的操作");
            Thread.sleep(5000);
            System.out.println("执行完毕，返回操作结果");
            result = "student list 50 ";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getRequest() {
        return result;
    }
}
