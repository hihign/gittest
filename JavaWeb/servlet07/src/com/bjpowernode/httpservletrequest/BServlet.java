package com.bjpowernode.httpservletrequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向请求域中绑定数据
        /*User user = new User("zhansan","male",20);
        req.setAttribute("UserInfo",user);*/
        Object obj = req.getAttribute("UserInfo");
        System.out.println(obj);
    }
}
