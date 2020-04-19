<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu bar</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light justify-content-center sticky-top">
    <ul class="navbar-nav">
        <li class="nav-item">
            <c:url value="/product/pizzas" var="pizzas"/>
            <a class="nav-link active" href="${pizzas}">Pizzas</a>
        </li>
        <li class="nav-item">
            <c:url value="/product/sides" var="sides"/>
            <a class="nav-link" href="${sides}">Sides</a>
        </li>
        <li class="nav-item">
            <c:url value="/product/drinks" var="drinks"/>
            <a class="nav-link" href="${drinks}">Drinks</a>
        </li>
    </ul>
</nav>
</body>
</html>
