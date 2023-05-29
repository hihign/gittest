<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用oa办公系统</title>
	</head>
	<body>
		<%--<%String contextPath = request.getContextPath();%>
		<a href="<%=contextPath%>/dept/list">查看部门信息</a>--%>
	<h1>login page</h1>
	<hr>
	<form action=<%=request.getContextPath()%>/match/user method="post">
		username<input type="text" name="username" /><br>
		password<input type="password" name="password" /><br>
		<input type="checkbox" name="f" value="1">十天内免登录</input>
		<input type="submit" value="login">
	</form>
	</body>
</html>
