<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<c:url var="User" value="pza">
    <c:param name="action" value="userShowList"/>
<%--    <jsp:useBean id="showClass" class="by.avdeev.pizzeria.action.user.UserShowAction"/>
    <c:param name="method" value="${showClass}"/>--%>
</c:url>
<%--<c:url var="AddUser" value="pza">
    <c:param name="action" value="userCreate"/>
</c:url>--%>
<a href="${User}">User</a>
<a href="sign-up.jsp">Create User</a>
<a href="sign-in.jsp">Sign in</a>
<c:url var="Goods" value="bs">
    <c:param name="command" value="goodsList"/>
</c:url>
<a href="${Goods}">Goods</a>
<c:url var="Item" value="bs">
    <c:param name="command" value="getItemList"/>
</c:url>
<a href="${Item}">Item</a>
</body>
</html>
