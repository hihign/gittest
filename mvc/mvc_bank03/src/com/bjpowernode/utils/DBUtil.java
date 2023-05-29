package com.bjpowernode.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private DBUtil(){}

    private static ResourceBundle bundle = ResourceBundle.getBundle("com.bjpowernode.resources.jdbc");
    private static String driver= bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    //注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        Connection conn  = DriverManager.getConnection(url,user,password);
        return conn;
    }

    //关闭资源
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
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
        if (conn != null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
