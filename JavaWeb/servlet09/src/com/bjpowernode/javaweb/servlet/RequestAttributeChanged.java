package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/change/request/attribute")
public class RequestAttributeChanged extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //存数据
        request.setAttribute("name","Jack");
        //替换数据
        request.setAttribute("name","Rose");
        //删除数据
        request.removeAttribute("name");
    }
}
