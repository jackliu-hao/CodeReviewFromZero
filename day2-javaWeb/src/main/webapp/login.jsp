<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-07-07
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>登录</title>
</head>
<body>

<%--如果已经登录，跳转到index.jsp--%>
<c:if test="${not empty sessionScope.user}">
    <c:redirect url="/index.jsp"/>
</c:if>



<%-- 获取项目名称 如 ip:prot/day2/xxx , 获取day2--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<form action="${contextPath}/user/login" method="post">
    <input type="text" name="loginName" placeholder="请输入用户名"><br>
    <input type="password" name="password" placeholder="请输入密码"><br>
<%--    单选框，管理员为1 ， 普通用户为0--%>
    管理员： <input type="radio" name="role" value="1"  >  </p>
    普通用户：<input type="radio" name="role" value="0"  >
    <input type="submit" value="登录">
</form>


</body>
</html>
