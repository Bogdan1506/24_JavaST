<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Nav</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light justify-content-center sticky-top">
    <ul class="navbar-nav">
        <li class="nav-item">
            <c:url value="/user/list" var="users"/>
            <a class="nav-link active" href="${users}">Users</a>
        </li>
        <li class="nav-item">
            <c:url value="/item/list" var="items"/>
            <a class="nav-link" href="${items}">Items</a>
        </li>
        <li class="nav-item">
            <c:url value="/order/list" var="orders"/>
            <a class="nav-link" href="${orders}">Orders</a>
        </li>
        <li class="nav-item">
            <c:url value="/orderposition/list" var="orderPos"/>
            <a class="nav-link" href="${orderPos}">Order Positions</a>
        </li>
        <li class="nav-item">
            <c:url value="/delivery/list" var="deliveries"/>
            <a class="nav-link" href="${deliveries}">Deliveries</a>
        </li>
    </ul>
</nav>
</body>
</html>

