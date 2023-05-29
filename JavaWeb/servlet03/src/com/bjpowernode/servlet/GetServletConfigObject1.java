package com.bjpowernode.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
/*
* 1、谁创建了ServletConfig？
*      Tomcat服务器
* 2、一个Servlet对象对应一个ServletConfig对象（配置信息）
* 3、ServletConfig对象包装的信息
*       <servlet></servlet>标签中的信息
* */

public class GetServletConfigObject1 extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //通过getServletConfig方法获取ServletConfig对象
        ServletConfig config = getServletConfig();

        /*ServletConfig对象中有哪些方法呢？
        * String String getServletName()
        * String getInitParameter(String name)
        * java.util.Enumeration<java.lang.String> getInitParameterNames()
        * */

        //方法getServletName()：获取Servlet对象的名字，即xml配置信息<servlet-name></servlet-name>标签中的name
        String name1 = config.getServletName();
        out.print(name1);
        out.print("<br>");
        //方法java.util.Enumeration<java.lang.String> getInitParameterNames()：获取Servlet对象初始化参数的名字，
        // 即xml配置信息<init-param>
        //                  <param-name></param-name>
        //                  <param-value></param-value>
        //           </init-param>标签中的param-name
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while(initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            out.print(name);
            out.print("<br>");
            //方法getInitParameter(String s)：获取Servlet对象中初始化参数值，即<param-value>中的值
            String value = config.getInitParameter(name);
            out.print(value);
            out.print("<br>");
        }
        //方法4：ServletContext getServletContext()
        //怎么获取ServletContext对象呢？
        //方式一：ServletConfig对象调用
        ServletContext servletContext = config.getServletContext();
        out.print("ServletContext对象为"+servletContext+"<br>");
        //方式二：Servlet类继承GenericServlet的getServletContext方法
        ServletContext servletContext1 = this.getServletContext();
        out.print("ServletContext对象为"+servletContext1+"<br>");
    }
}
