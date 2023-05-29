<%@ page import="com.bjpowernode.oa3.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详情</title>
		<%--设置整个网页的基础路径,只能设置HTML中的基础路径，不设置JS代码中的基础路径为它
			http://localhost:8080/0a/
		--%>
		<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/"/>

	</head>
	<body>
	<script type="text/javascript">
		function del(dno){
			if (window.confirm("亲，删了不可恢复哦")){
				document.location.href="${pageContext.request.contextPath}/dept/delete?deptno="+dno
			}
		}
	</script>
		<h3>欢迎${user.username}，在线人数:${applicationScope.userObject}</h3>
		<h1 align='center'>部门详情</h1>
		<hr>
		<a href="user/exit">安全退出</a>
		<table border="1px" width="50%" align="center">
			<tr>
				<th>序号</th>
				<th>编号</th>
				<th>名称</th>
				<th>所在地</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${deptList}" var="dept" varStatus="varStatus">


				<tr>
				<td>${(varStatus.count)}</td>
				<td>${dept.deptno}</td>
				<td>${dept.dname}</td>
				<td>${dept.loc}</td>
				<td>
				<a href="${pageContext.request.contextPath}/dept/detail?f=d&deptno=${dept.deptno}">详情</a>
				<a href="${pageContext.request.contextPath}/dept/detail?f=m&deptno=${dept.deptno}">修改</a>
				<a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
				</td>
				</tr>
			</c:forEach>





		</table>
		<hr>
		<a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>
	</body>
</html>
