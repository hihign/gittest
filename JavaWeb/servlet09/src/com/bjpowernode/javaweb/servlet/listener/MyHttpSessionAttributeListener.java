package com.bjpowernode.javaweb.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent hsbe){
        System.out.println("数据被存到了session域中");
    }

    public void attributeReplaced(HttpSessionBindingEvent hsbe){
        System.out.println("session域中的数据被替换！");
    }

    public void  attributeRemoved(HttpSessionBindingEvent hsbe){
        System.out.println("session域中的数据被删除！");
    }
}
