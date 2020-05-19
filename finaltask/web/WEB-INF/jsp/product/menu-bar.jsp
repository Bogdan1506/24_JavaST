<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu bar</title>
    <style>
        .font {
            font-size: 21px;
        }
    </style>
</head>
<body>
<img class="mx-auto d-block" src="../../../img/header.jpg" alt=""/>
    <nav class="navbar navbar-expand-sm bg-light justify-content-center sticky-top font">
        <ul class="navbar-nav">
            <li class="nav-item">
                <c:url value="/product/pizzas" var="pizzas"/>
                <a class="nav-link" href="${pizzas}"><fmt:message key="pizzas" bundle="${rb}"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/product/sides" var="sides"/>
                <a class="nav-link" href="${sides}"><fmt:message key="sides" bundle="${rb}"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/product/drinks" var="drinks"/>
                <a class="nav-link" href="${drinks}"><fmt:message key="drinks" bundle="${rb}"/></a>
            </li>
        </ul>
    </nav>
</body>
</html>
