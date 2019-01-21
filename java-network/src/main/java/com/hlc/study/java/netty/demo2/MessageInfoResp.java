package com.hlc.study.java.netty.demo2;

import java.io.Serializable;

/**
    *@ClassName MessageInfoResp
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 9:48
    *@Version 1.0
**/

public class MessageInfoResp implements Serializable {

    private int resqid;
    private int respCode;
    private String message;

    public int getResqid() {
        return resqid;
    }

    public void setResqid(int resqid) {
        this.resqid = resqid;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageInfoResp{" +
                "resqid=" + resqid +
                ", respCode=" + respCode +
                ", message='" + message + '\'' +
                '}';
    }

}
