package com.bjpowerndoe.serlvet;
import jakarta.servlet.*;

import java.io.IOException;

public class ServletLifecycle implements Servlet{
    //使用无参的构造方法，测试Tomcat服务器在启动时，是否创建Servlet对象
    public ServletLifecycle(){
        System.out.println("ServletLifecycle无参的构造方法执行了");
    }
    //有参构造,浏览器页面报500
    /*   public ServletLifecycle(String s){

    }*/
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init's method execute...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service's method execute...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    //该方法在服务器关闭时执行，关闭服务器意味着需要销毁Servlet对象
    //并且如果这里还有其他资源还未被关闭，这里都可以将他们关闭了
    public void destroy() {
        System.out.println("服务器即将关闭，请做好准备！");
    }
}
/*
1、无参构造方法并没有执行，说明服务器启动时并不创建Servlet对象
2、a、用户第一次访问Tomcat服务器，Tomcat服务器会创建Servlet对象,
   然后紧接着执行init方法，再去执行service方法
   b、用户再次去访问时，执行了service方法，无参构造和init方法并没有执行，说明Tomcat只创建一个对象
3、在关闭服务器时执行destroy方法，执行该方法时，并未将Servlet对象销毁，因为destroy方法为实例方法
4、手动提供有参构造，不提供无参构造，Tomcat还会正常执行吗？
        会报错误500，这是Java代码出现了问题
        结论：我们程序员最好不要写构造方法，我们提供构造方法可能会造成Tomcat实例化异常

5、Tomcat服务器除了执行构造方法（创建对象），还会执行init方法
       --把init方法中的内容放入构造方法中不行吗？为什么还需要init方法?
        Servlet规范中有要求，作为javaweb程序员，编写Servlet类的时候，不建议手动编写构造方法，因为编写构造方法，很容易让无参数构造方法消失，
        这个操作可能会导致Servlet对象无法实例化。所以init方法是有存在的必要的。
*/