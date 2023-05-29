<%--
  Created by IntelliJ IDEA.
  User: Asher
  Date: 2023/4/14
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<a href="javascript:showStu()">发送AJAX请求</a>
<div id="mydiv"></div>
<script type="text/javascript">
    function showStu(){
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/student.action",
            dataType:"json",
            success:function (stuList){
                var s = "";
                $.each(stuList,function (i,stu){
                    s+=stu.name+":"+stu.age+"<br>";
                });
                $("#mydiv").html(s);
            }
        })
    }

</script>
</body>
</html>
