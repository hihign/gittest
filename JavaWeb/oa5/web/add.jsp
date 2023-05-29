<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增部门</title>
	</head>
	<body>
	<h3>欢迎，${username}</h3>
		<h1>新增部门</h1>
		<form action="${pageContext.request.contextPath}/dept/save" method="post">
			部门编号<input type="text" name="deptno" /><br>
			部门名称<input type="text" name="dname" /><br>
			部门所在地<input type="text" name="loc" /><br>
			<input type="submit" value="保存" />
		</form>
	</body>
</html>