package com.bjpowernode.ajax.encapsulation;

import com.alibaba.fastjson.JSON;
import com.bjpowernode.ajax.noencapsulation.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/request2")
public class AjaxRequestServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        User user = new User(username);
        String jsonStr = JSON.toJSONString(user);
        out.print(jsonStr);
    }
}
