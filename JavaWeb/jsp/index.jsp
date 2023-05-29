<%@page contentType="text/html8" pageEncoding="UTF-8"%>

<%--当前页面出异常，跳转到错误页面--%>
<%@page errorPage="/error.jsp"%>
<%
    int a = 10;
    int b = 0;
    out.write(a/b);
%>