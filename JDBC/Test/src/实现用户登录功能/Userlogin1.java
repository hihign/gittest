package 实现用户登录功能;
/*解决了Userlogin类中SQL注入问题
* 但Statement仍然存在，说明存在即合理
*
* 业务需求：1、SQL语句采用字符串拼接方式
*           2、存在SQL注入（忘记密码时可以采用这种方式登录）*/
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Userlogin1 {
    public static void main(String[] args) {
        Map<String,String> userInfo = initUI();
        boolean success = log(userInfo);
        System.out.println(success? "登录成功":"登陆失败");
    }

    /**
     *
     * @param userInfo 用户的信息，包括用户名和密码
     * @return 返回的是对用户输入的用户信息的一个验证结果，true表示匹配成功，false则匹配失败
     *
     */
    private static boolean log(Map<String, String> userInfo) {
        String userName = userInfo.get("userName");
        String password = userInfo.get("password");
        boolean flag = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","Mr.Feng1");
            String sql = "select username,userpassword from t_userlogin where username = ? and userpassword = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,password);
            rs = statement.executeQuery();
            if (rs.next()){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return  flag;
    }

    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = s.nextLine();
        System.out.println("请输入密码");
        String password = s.nextLine();
        Map<String,String> userInfo= new HashMap<>();
        userInfo.put("userName",userName);
        userInfo.put("password",password);
        return userInfo;
    }
}
