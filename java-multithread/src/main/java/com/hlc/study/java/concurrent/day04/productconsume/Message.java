package com.hlc.study.java.concurrent.day04.productconsume;

import java.io.Serializable;

/**
    *@ClassName Data
    *@Description todo
    *@Author Liang
    *@Date 2019/1/15 9:36
    *@Version 1.0
**/

public class Message implements Serializable {
    private int id;
    private String name;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
