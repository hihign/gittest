<%@page contentType="text/html;charset=UTF-8" %>
<%@page isErrorPage="true"%>

<%--将该页设置为错误页面，这样内置的异常对象就可以使用了
如果不手动设置，默认该页不为错误页面，那么对应的异常对象就不能用，不能向服务器抛出异常，
这样对用户时友好的，但是我们程序员不知道哪里出问题了，增加我们的维护成本
--%>
<%exception.printStackTrace();%>

<h1>网络繁忙，请稍后再试！</h1>