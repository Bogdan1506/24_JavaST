<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
<h1>Item List</h1>
<form action="bs">
    <input type="hidden" name="command" value="goodsList">
    <input type="submit" value="Back">
</form>
<table border="2">
    <thead>
    <th scope="row">id</th>
    <th scope="row">goodsId</th>
    <th scope="row">dough</th>
    <th scope="row">size</th>
    </thead>
    <tbody>
    <c:forEach var="temp" items="${items}" varStatus="status">
        <tr>
            <td><c:out value="${temp.id}"/></td>
            <td><c:out value="${temp.goodsId}"/></td>
            <td><c:out value="${temp.dough}"/></td>
            <td><c:out value="${temp.size}"/></td>
            <c:url var="DeleteGoodsLink" value="bs">
                <c:param name="command" value="deleteItem"/>
                <c:param name="id" value="${temp.id}"/>
            </c:url>
            <td><a href="${DeleteGoodsLink}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
