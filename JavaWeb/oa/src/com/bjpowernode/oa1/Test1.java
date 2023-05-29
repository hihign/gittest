package com.bjpowernode.oa1;

import com.bjpowernode.resource.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
             conn = DBUtil.getConnection();
             String sql = "insert into t_dt(deptno,dname,loc) values(4,4,4)";
             ps = conn.prepareStatement(sql);
             count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if (count==1) {
            request.getRequestDispatcher("/s").forward(request,response);
        }

    }
}
