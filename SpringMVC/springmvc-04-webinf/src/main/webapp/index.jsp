<%@ page contentType="text/html;charset=utf-8" language="java"%>
<html>
<body>
<h2>登录....</h2>
<form action="${pageContext.request.contextPath}/showMainPlus">
    <input name="username"/><br>
    <input type="password" name="passwd" /><br>
    <input type="submit" value="登录" />
</form>
${msg}
</body>
</html>
