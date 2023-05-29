<%@ page import="com.bjpowernode.javaweb.el.User" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%--EL表达式中的运算符
  算数运算：+ - * / %
  关系运算：!= == >= <= eq
  逻辑运算：！ && ||  not and or
  empty运算：运算结果为boolean类型
  三目运算 ? :
--%>

<%--算数运算
    + 只做求和运算，会将不是实数的字符转换成实数。如果不能转换，报错误500
--%>
${10+20}<br>
${10+"20"}<br>
\${"abc"+10} <br>
${10%7} <br>

<%--关系运算--%>
<%
    String s1 = new String("abc");
    String s2 = new String("abc");
    request.setAttribute("s1",s1);
    request.setAttribute("s2",s2);


    User user1 = new User(20,"Rose","root");
    User user2 = new User(20,"Rose","root");
    request.setAttribute("user1",user1);
    request.setAttribute("user2",user2);
%>

<%--
 ==
底层调用了equals方法--%>
${s1 == s2}<br>
${user1 eq user2}<br>

<%--empty--%>
${empty pageContext.request.getAttribute("user1")}<br>

<%-- >=  <= --%>
${user1 > user2}<br>
${user1 < user2}<br>

<%--三木运算符--%>
${empty param.username? "用户名为空":"登录成功"}