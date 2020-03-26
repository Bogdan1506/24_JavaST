<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<c:url var="User" value="cs">
    <c:param name="method" value="showUserList"/>
<%--    <jsp:useBean id="showClass" class="by.avdeev.pizzeria.action.UserShowAction"/>
    <c:param name="method" value="${showClass}"/>--%>
</c:url>
<a href="${User}">User</a>
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
