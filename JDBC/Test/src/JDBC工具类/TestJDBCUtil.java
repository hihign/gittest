package JDBC工具类;

import java.sql.*;

public class TestJDBCUtil {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "select empno,ename from emp where ename = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"SMITH");
             rs = ps.executeQuery();
             if (rs.next()){
                 String s1 = rs.getString("empno");
                 String s2 = rs.getString("ename");
                 System.out.println(s1+","+s2);
             }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JDBCUtil.resourceClose(connection,ps,rs);
        }

    }
}
