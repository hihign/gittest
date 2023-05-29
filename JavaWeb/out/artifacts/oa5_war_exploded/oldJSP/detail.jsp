<%@ page import="com.bjpowernode.oa3.Dept" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详细信息</title>
	</head>
	<body>
	<h3>欢迎，${sessionScope.username}</h3>
		<h1>部门详细信息</h1>
		部门编号：${dept.deptno}<br>
		部门名称：${dept.dname}<br/>
		部门所在地：${dept.loc}<br/>
		<input type="button" value="后退" onclick="window.history.back()" />
	</body>
</html>
