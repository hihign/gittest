<%@page contentType="text/html;charset=UTF-8" %>
<%--EL表达式中其他隐式对象
  pageContext
  param
  paramValues
  initParam
--%>

<%--param和paramValues都是用来获取用户的提交的数据
    但是param只获取用户提交的第一个参数的值
--%>

<%--请求路径为：http://localhost:8080/jsp/5.jsp?username=zhangsan--%>
${param.username}<br>

<%--请求路径为：http://localhost:8080/jsp/5.jsp?username=zhangsan&username=Jack&username=Rose--%>
${paramValues.username} <br><%--获取的是一个数组--%>
用户名：${paramValues.username[0]}、${paramValues.username[1]}、${paramValues.username[2]}<br>

<%--initParam是EL表达式获取上下文初始化参数的对象--%>
每页显示的记录条数：${initParam.pageSize}<br>
一共多少页：${initParam.pageNum}<br>

