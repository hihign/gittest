<%@ page import="com.bjpowernode.oa3.Dept" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>修改部门信息</title>
</head>
<body>
<h3>欢迎，${sessionScope.username}</h3>
<form action="<%=request.getContextPath()%>/dept/modify">
  部门编号<input type="text" name="deptno" value=${dept.deptno}readonly /><br>
  部门名称<input type="text" name="dname" value=${dept.dname} /><br>
  部门所在地<input type="text" name="loc" value=${dept.loc} /><br>
  <input type="submit" value="保存" />
</form>
</body>
</html>
