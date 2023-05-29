<%@page contentType="text/html;charset=UTF-8"%>
<%
    pageContext.setAttribute("data","pa");
    request.setAttribute("data","re");
    session.setAttribute("data","se");
    application.setAttribute("data","ap");
%>
<%--优先从范围小的域中取数据--%>
${data}

<%--EL表达式中4个隐含的对象,作用:从指定的域中取数据
    pageScope
    requestScope
    sessionScope
    applicationScope
--%>
${requestScope.data}
${pageScope.data}
${sessionScope.data}
${applicationScope.data}