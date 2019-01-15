package com.hlc.study.java.concurrent.day04.future;
/**
    *@ClassName Main
    *@Description todo
    *@Author Liang
    *@Date 2019/1/14 17:04
    *@Version 1.0
**/

public class Main {

    public static void main(String[] args) {
        FutureClient client=new FutureClient();
        //获取数据
        DataRequest data = client.request("请求参数");

        System.out.println("请求发送成功!");
        System.out.println("做其他的事情...");

        //返回数据
        String result = data.getRequest();
        System.out.println("查询结果集： "+result);
    }
}
