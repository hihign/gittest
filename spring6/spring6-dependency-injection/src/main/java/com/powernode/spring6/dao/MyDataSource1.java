package com.powernode.spring6.dao;

import java.util.Properties;

public class MyDataSource1 {
    Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyDataSource1{" +
                "properties=" + properties +
                '}';
    }
}
