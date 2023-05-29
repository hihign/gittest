<%--
  Created by IntelliJ IDEA.
  User: Asher
  Date: 2023/4/14
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/request2page1.action">请求转发到页面</a> <br>
    <a href="${pageContext.request.contextPath}/request2action1.action">请求转发到action</a> <br>
    <a href="${pageContext.request.contextPath}/request2page2.action">请求重定向到页面</a><br>
    <a href="${pageContext.request.contextPath}/request2action2.action">请求重定向到action</a> <br>

</body>
</html>
