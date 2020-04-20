<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart</title>
</head>
<body>
<div class="sticky-top pt-5">
    <p class="display-4">Cart</p>
    <c:choose>
        <c:when test="${empty sessionScope.cart}">
            <p style="color: red">Your cart is empty now</p>
        </c:when>
        <c:otherwise>
            <p>
                <c:url value="/item/list/remove" var="removeAll">
                    <c:param name="id" value="all"/>
                </c:url>
                <a style="color: red" href="${removeAll}">Reset cart</a>
            </p>
        </c:otherwise>
    </c:choose>
    <c:forEach var="temp" items="${cart}">
        <p>
            <img alt="" src="../../../static/img/${temp.product.name}.jpg" width="60" height="60">
            <c:out value="${temp.product.name}"/>
            <c:out value="${temp.dough}"/>
            <c:out value="${temp.size}"/>
            <c:url value="/item/list/remove" var="remove">
                <c:param name="id" value="${temp.id}"/>
            </c:url>
            <a style="color:red;" href="${remove}">X</a>
        </p>
    </c:forEach>
    <c:if test="${not empty cart}">
        <c:url value="/item/list/orderPosition" var="orderPosition"/>
        <form action="${orderPosition}">
            <input class="btn btn-warning" type="submit" value="Order">
        </form>
    </c:if>
</div>
</body>
</html>
