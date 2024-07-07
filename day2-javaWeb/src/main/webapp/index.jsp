<%@ page import="com.llu.bean.CommonBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1> 登录成功啦  </h1>
<%-- 使用JSTL表达式 遍历request.getAttribute("user") 获取到user对象，然后获取到user对象的属性值，然后输出到页面上--%>

    <%
        List<CommonBean> users = (List<CommonBean>) request.getSession().getAttribute("users");
        pageContext.setAttribute("users", users);
        System.out.println(users);
    %>

<c:forEach items="${users}" var="user">
   用户名： <c:out value="${user.name}"/><br>
   登录名：  <c:out value="${user.loginName}"/><br>
   邮箱： <c:out value="${user.email}"/><br>
   电话号： <c:out value="${user.phone}"/><br>
   角色： <c:out value="${user.role}"/><br>
</c:forEach>

<%-- 退出登录--%>

<a href="<%=request.getContextPath()%>/user/logout">退出登录</a>



</body>

</html>