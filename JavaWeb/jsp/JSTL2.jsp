<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--if标签
test属性是必须的，其属性值为boolean类型.支持EL表达式--%>
<c:if test="${not empty param.username}">
  <h1>欢迎${param.username}回家</h1>
  <br>
</c:if>


<%--var的属性值存放的是test的属性值，scope用来指定var存储的域--%>
<c:if test="${not empty param.username}" var="v" scope="page">
  <h1>欢迎你，${param.username}</h1>
</c:if>

<%--将pageContext域中存储的v取出来--%>
${v}

<%--var属性用来指定循环中的变量--%>
<c:forEach var="i" begin="1" end="10" step="1">
  <%--这里能取出i，肯定底层将i放到某个域中--%>
  ${i}
</c:forEach>