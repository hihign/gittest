package com.bjpowernode.oa3;

import com.bjpowernode.resource.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet({"/welcome"})
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)){
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
            if (username!=null&&password!=null){
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                boolean success = false;
                try {
                    conn = DBUtil.getConnection();
                    String sql = "select username,password from t_user where username = ? and password = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1,username);
                    ps.setString(2,password);
                    rs = ps.executeQuery();
                    if (rs.next()){
                        success = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    DBUtil.close(conn,ps,rs);
                }
                if (success){
                    //跳转到部门列表页面
                    HttpSession session = request.getSession();
                    session.setAttribute("username",username);
                    response.sendRedirect(request.getContextPath()+"/dept/list");
                }else {
                    //跳转到失败页面，重新登陆
                    response.sendRedirect("/error.jsp");
                }
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }

    }
}
