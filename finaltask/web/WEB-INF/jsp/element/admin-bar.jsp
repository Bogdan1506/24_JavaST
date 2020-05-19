<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Nav</title>
    <style>
        .font {
            font-size: 21px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light justify-content-center sticky-top font container">
    <ul class="navbar-nav">
        <li class="nav-item">
            <c:url value="/user/list" var="users"/>
            <a class="nav-link" href="${users}"><fmt:message key="users" bundle="${rb}"/></a>
        </li>
        <li class="nav-item">
            <c:url value="/order/list" var="orders"/>
            <a class="nav-link" href="${orders}"><fmt:message key="orders" bundle="${rb}"/></a>
        </li>
        <li class="nav-item">
            <c:url value="/orderposition/list" var="orderPos"/>
            <a class="nav-link" href="${orderPos}"><fmt:message key="orderPos" bundle="${rb}"/></a>
        </li>
        <li class="nav-item">
            <c:url value="/delivery/list" var="deliveries"/>
            <a class="nav-link" href="${deliveries}"><fmt:message key="deliveries" bundle="${rb}"/></a>
        </li>
    </ul>
</nav>
</body>
</html>

