package com.bjpowernode.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/request2")
public class AjaxRequest1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "Mr.Feng1";
            conn = DriverManager.getConnection(url,user,password);
            String sql = "select name,age,addr from t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //这种拼接太繁琐，利用JSON组件，阿里巴巴的，已无偿捐给apache软件基金会了
           /* StringBuffer sb = new StringBuffer();
            sb.append("[");
            while(rs.next()){
                String name = rs.getString("name");
                Long  age  = rs.getLong("age");
                String addr = rs.getString("addr");
                sb.append("{\"name\":\" ");
                sb.append(name);
                sb.append("\", \"age\":");
                sb.append(age);
                sb.append(", \"addr\":\"");
                sb.append(addr);
                sb.append("\"},");
            }
            String jsonStr = sb.substring(0,sb.length()-1)+"]";
            out.print(jsonStr);*/
            while(rs.next()){
                String name = rs.getString("name");
                Long  age  = rs.getLong("age");
                String addr = rs.getString("addr");

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
