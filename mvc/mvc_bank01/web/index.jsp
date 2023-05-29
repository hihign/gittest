<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎进入银行转账系统</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/transfer" method="post">
    转出账户：<input type="text" name="fromActno" /><br>
    转入账户：<input type="text" name="toActno" /><br>
    转账金额：<input type="text" name="money" /><br>
    <input type="submit" value="提交">
  </form>
  </body>
</html>
