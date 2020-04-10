<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Menu Bar</title>
</head>
<body>
<ul class="nav justify-content-center">
    <li class="nav-item">
        <c:url value="/product/pizzas" var="pizzas"/>
        <a class="nav-link active" href="${pizzas}">Pizzas</a>
    </li>
    <li class="nav-item">
        <c:url value="/product/sides" var="sides"/>
        <a class="nav-link" href="${sides}">Sides</a>
    </li>
    <c:url value="/product/drinks" var="drinks"/>
    <li class="nav-item">
        <a class="nav-link" href="${drinks}">Drinks</a>
    </li>
</ul>
</body>
</html>
