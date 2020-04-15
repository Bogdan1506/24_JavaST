<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>NavBar</title>
    <link rel="stylesheet" type="text/css" href="../../static/css/main-bar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<link rel="icon" href="data:,">
<div class="header">
    <img src="../../static/img/header.jpg" alt="">
</div>
<div class="navbar">
    <c:url value="/" var="menu"/>
    <a href="${menu}">Menu</a>
    <div class="right">
        <a class="right" href="#">Contacts</a>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <c:url value="/profile/user" var="profileShow"/>
                <a href="${profileShow}" style="  border-style: solid;
  border-color: red;"> ${sessionScope.user.login}</a>
                <c:url value="/user/sign-out" var="userSignOut"/>
                <a href="${userSignOut}">Sign out</a>
            </c:when>
            <c:otherwise>
                <c:url value="/user/sign-in" var="userSignIn"/>
                <a href="${userSignIn}"> Sign in</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <c:url value="/user/list" var="users"/>
            <a class="nav-link" href="${users}">Users</a>
        </c:if>
        <div class="dropdown">
            <button class="dropbtn">Local
                <em class="fa fa-caret-down"></em>
            </button>
            <div class="dropdown-content">
                <a href="#">EN</a>
                <a href="#">RU</a>
                <a href="#">BE</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>