<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Nav</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <style>
        .font {
            font-size: 21px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-inverse bg-light font">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <c:url var="menu" value="/"/>
                <a class="nav-link" href="${menu}">Menu</a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <li>
                <a class="nav-link" href="#">Contacts</a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <c:url value="/profile/user" var="profileShow"/>
                    <li class="nav-item">
                        <a class="nav-link border border-success" href="${profileShow}">${sessionScope.user.login}</a>
                    </li>
                    <c:url value="/user/sign-out" var="userSignOut"/>
                    <li class="nav-item">
                        <a class="nav-link" href="${userSignOut}">Sign out</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:url value="/user/sign-in" var="userSignIn"/>
                    <a class="nav-link" href="${userSignIn}"> Sign in</a>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <c:url value="/user/list" var="users"/>
                <a class="nav-link" href="${users}">Abilities</a>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <em class='fas fa-globe-americas'></em>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">EN</a>
                    <a class="dropdown-item" href="#">RU</a>
                    <a class="dropdown-item" href="#">BE</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>

