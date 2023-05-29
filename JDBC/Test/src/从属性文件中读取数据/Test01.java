package 从属性文件中读取数据;
/*使用资源绑定器绑定配置文件的前提：
*       配置文件必须在src路径下*/
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ResourceBundle;

public class Test01 {
    public static void main(String[] args) {
    ResourceBundle bundle = ResourceBundle.getBundle("classes");
    String driver = bundle.getString("driver");
    String url = bundle.getString("url");
    String user = bundle.getString("user");
    String passwd = bundle.getString("passwd");
    Connection cn = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
        cn = DriverManager.getConnection(url, user, passwd);
        statement = cn.createStatement();
        String sql = "select empno,ename from emp where ename = 'SMITH'";
        rs = statement.executeQuery(sql);
        if (rs.next()){
            String column1 = rs.getString("empno");
            String column2 = rs.getString("ename");
            System.out.println(column1+","+column2);
        }
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        if (statement != null){
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
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
    }
}
