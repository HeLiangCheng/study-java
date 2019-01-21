package com.hlc.study.java.netty.demo2;

import java.io.Serializable;

/**
    *@ClassName MessageInfoReq
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 9:48
    *@Version 1.0
**/

public class MessageInfoReq implements Serializable {

    private Integer reqid;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;

    public Integer getReqid() {
        return reqid;
    }

    public void setReqid(Integer reqid) {
        this.reqid = reqid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MessageInfoReq{" +
                "reqid=" + reqid +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
