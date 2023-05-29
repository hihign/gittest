<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${param.age < 18}">
        <h1>少年</h1>
    </c:when>
    <c:when test="${param.age < 35}">
        <h1>青年</h1>
    </c:when>
    <c:when test="${param.age < 50}">
        <h1>壮年</h1>
    </c:when>
    <c:otherwise>
        <h1>老年</h1>
    </c:otherwise>
</c:choose>