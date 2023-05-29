package com.bjpowernode.httpservletrequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestTest02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从RequestTest1请求域中取属性值,要保证是同一次请求
        System.out.println(req.getAttribute("UserInfo"));
    }
}
