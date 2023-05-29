package JDBC工具类;

import java.sql.*;

public class JDBCUtil {
    private  JDBCUtil(){}
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws  SQLException{
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","Mr.Feng1");

    }

    public static void resourceClose(Connection cn, Statement statement, ResultSet resultSet){
        if (resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (statement != null){
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (cn != null){
            try{
                cn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
