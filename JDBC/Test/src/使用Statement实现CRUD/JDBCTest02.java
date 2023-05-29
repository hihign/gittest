package 使用Statement实现CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
* 1、注册驱动
* 2、获取连接
* 3、获取数据库操作对象
* 4、执行SQL
* ６、释放资源*/
public class JDBCTest02 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bjpowernode","root","Mr.Feng1");
            statement = connection.createStatement();
            String sql = "insert into dept(deptno,dname,loc) values(50,'销售部','上海')";
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "修改成功！" : "修改失败！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
