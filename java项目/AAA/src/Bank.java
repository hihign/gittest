//为了Java小程序能与Tomcat服务器解耦合
//我们（webapp的开发者）实现了这个接口
import javax.Servlet;
public class Bank implements Servlet{
    public void service(){
        System.out.println("Bank小程序已开启");
    }
}