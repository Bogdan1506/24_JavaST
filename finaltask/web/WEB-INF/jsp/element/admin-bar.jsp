<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Nav</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light justify-content-center sticky-top">
    <ul class="navbar-nav">
        <li class="nav-item">
            <c:url value="/product/pizzas" var="users"/>
            <a class="nav-link active" href="${pizzas}">Users</a>
        </li>
        <li class="nav-item">
            <c:url value="/product/sides" var="users"/>
            <a class="nav-link" href="${sides}">Items</a>
        </li>
        <li class="nav-item">
            <c:url value="/product/drinks" var="users"/>
            <a class="nav-link" href="${drinks}">Orders</a>
        </li>
        <li class="nav-item">
            <c:url value="/product/drinks" var="users"/>
            <a class="nav-link" href="${drinks}">Deliveries</a>
        </li>
    </ul>
</nav>
</body>
</html>

