<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart</title>
</head>
<body>
<div class="container">
    <h3>Cart</h3>
    <c:choose>

        <c:when test="${empty sessionScope.cart}">
            <p>Your cart is empty now</p>
        </c:when>
        <c:otherwise>
            <p>
                <c:url value="/item/list/remove" var="removeAll">
                    <c:param name="id" value="all"/>
                </c:url>
                <a href="${removeAll}">X all</a>
            </p>
        </c:otherwise>
    </c:choose>
    <table id="tab" class="table table-borderless">
        <tbody>
        <c:forEach var="temp" items="${cart}">
            <%--            <c:set value="${temp}" var="item"/>
                        <c:set value="${0}" var="counter"/>
                        <c:forEach var="count" items="${cart}" varStatus="el">
                            <c:if test="${item eq el}">
                                ${counter}
                            </c:if>
                        </c:forEach>--%>
            <tr>
                <td>
                    <p>
                        <img alt="" src="${temp.product.picture}" width="45" height="45">
                        <c:url value="/item/list/remove" var="remove">
                            <c:param name="id" value="${temp.id}"/>
                        </c:url>
                        <a href="${remove}">X</a>
                    </p>
                    <h3>
                        <c:out value="${temp.product.name}"/>
                    </h3>
                    <p>${temp.dough}</p>
                    <p>${temp.size}</p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty cart}">
        <c:url value="/item/list/order" var="order"/>
        <form action="${order}">
            <input class="btn btn-warning" type="submit" value="Order">
        </form>
    </c:if>
</div>
</body>
</html>
