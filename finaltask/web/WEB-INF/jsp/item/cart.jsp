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
    <p style="text-align: center;" class="display-4">Cart</p>
    <c:choose>
    <c:when test="${empty sessionScope.cart}">
        <p style="color: red; text-align: right">Your cart is empty now</p>
    </c:when>
    <c:otherwise>
    <p>
        <c:url value="/item/cart/remove" var="removeAll">
            <c:param name="id" value="all"/>
        </c:url>
        <strong>
            <a style="color: red;" href="${removeAll}">Reset cart</a>
        </strong>
    </p>
    <div class="overflow-auto p-3 mb-3 mb-md-0 mr-md-3 bg-light" style="max-width: 500px; max-height: 500px;">
        </c:otherwise>
        </c:choose>
        <c:forEach var="temp" items="${cart}">
            <p>
                <img alt="" src="${temp.product.picture}" width="60" height="60">
                <strong>
                    <c:out value="${temp.product.name}"/>
                </strong>
                <c:url value="/item/cart/remove" var="remove">
                    <c:param name="id" value="${temp.id}"/>
                </c:url>
                <a style="color:red;" href="${remove}">X</a>
                <br/>
                <c:out value="${temp.dough}"/>
                <br/>
                <c:out value="${temp.size}"/>
            </p>
            <%--            <hr/>--%>
        </c:forEach>
    </div>
    <c:if test="${not empty cart}">
        <c:url value="/item/cart/order" var="createItems"/>
        <form action="${createItems}">
            <input class="btn btn-lg btn-warning" type="submit" value="Order">
        </form>
    </c:if>
</div>
</body>
</html>
