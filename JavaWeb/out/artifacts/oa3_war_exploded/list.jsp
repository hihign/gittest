<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详情</title>
	</head>
	<body>
		<h1 align='center'>部门详情</h1>
		<hr>
		<table border="1px" width="50%" align="center">
			<tr>
				<th>序号</th>
				<th>编号</th>
				<th>名称</th>
				<th>所在地</th>
				<th>操作</th>
			</tr>
			<% int count = 0;%>
			<tr>
				<td><%=(++count)%></td>
				<td></td>
				<td>销售部</td>
				<td>北京</td>
				<td>
					<a href="<%=request.getContextPath()%>/detail.jsp">详情</a>
					<a href="<%=request.getContextPath()%>/update.jsp">修改</a>
					<a href="javascript:void(0)" onclick="window.confirm('请确认删除吗？')">删除</a>
				</td>
			</tr>

		</table>
		<hr>
		<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
	</body>
</html>
