package com.bjpowernode.ajax.encapsulation;

import com.alibaba.fastjson.JSON;
import com.bjpowernode.ajax.encapsulation.beans.Area;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/request03")
public class AjaxRequestServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pcode = request.getParameter("pcode");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        String user = "root";
        String password = "Mr.Feng1";
        List<Area> areaList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            String sql = "";
            if (pcode == null){
                sql = "select code,name from t_area where pcode is null";
                ps = conn.prepareStatement(sql);
            }else {
                sql = "select code,name from t_area where pcode=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(pcode));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                 int code = rs.getInt("code");
                String name = rs.getString("name");
                Area area = new Area(code,name);
                areaList.add(area);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        String jsonStr = JSON.toJSONString(areaList);
        response.getWriter().print(jsonStr);
    }
}
