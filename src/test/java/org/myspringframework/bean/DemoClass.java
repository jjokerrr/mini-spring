package org.myspringframework.bean;

public class DemoClass{
    private final String msg;

    public DemoClass(){
        this("hello world!");
    }

    public DemoClass(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}