<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-07-07
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%--         XSS --%>
        登录失败:  <%=request.getAttribute("loginName") %> <br />
        <a href=${contextPath}/login.jsp>重新登录</a>
</body>
</html>
