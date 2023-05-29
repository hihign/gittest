package com.powernode.spring6.dao;

public class BeanStepFive {
    String name;

    public BeanStepFive() {
        System.out.println("1.实例化对象");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("2.给属性赋值");
    }

    public void initBean(){
        System.out.println("3.初始化Bean");
    }

    public void destroyBean(){
        System.out.println("5.销毁Bean");
    }
}
