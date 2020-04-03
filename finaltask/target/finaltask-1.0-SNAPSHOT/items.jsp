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
    <c:forEach var="temp" items="${itemList}" varStatus="status">
        <tr>
            <td><c:out value="${temp.goods.name}"/></td>
            <td>
                <img alt="" src="${temp.goods.picture}"/>
            </td>
            <td><c:out value="${temp.dough}"/></td>
            <td><c:out value="${temp.size}"/></td>
            <td><c:out value="${temp.goods.price * temp.size.coefficient}"/></td>

<%--            <c:set var="result" value="${result * temp.goods.price}"/>--%>

            <c:url var="DeleteGoodsLink" value="bs">
                <c:param name="command" value="deleteItemFromList"/>
                <c:param name="goodsId" value="${temp.goods.id}"/>
                <c:param name="goodsName" value="${temp.goods.name}"/>
                <c:param name="goodsPrice" value="${temp.goods.price}"/>
                <c:param name="goodsPicture" value="${temp.goods.picture}"/>
                <c:param name="goodsDescription" value="${temp.goods.description}"/>
                <c:param name="dough" value="${temp.dough}"/>
                <c:param name="size" value="${temp.size}"/>
            </c:url>

<%--            <c:out value="${result}"/>--%>

            <td><a href="${DeleteGoodsLink}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
