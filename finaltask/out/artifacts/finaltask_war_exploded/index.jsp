<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 09.03.2020
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<c:url var="User" value="bs">
    <c:param name="command" value="list"/>
</c:url>
<a href="${User}">User</a>
<c:url var="Goods" value="bs">
    <c:param name="command" value="goodsList"/>
</c:url>
<a href="${Goods}">Goods</a>
<%--  <img alt="" src="https://d1doqjmisr497k.cloudfront.net/-/media/mccormick-us/recipes/grill-mates/g/800/grilled-chicken-greek-pizza.jpg"/>--%>
</body>
</html>
