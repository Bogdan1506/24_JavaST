<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
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
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
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
                <a class="nav-link" href="${menu}"><fmt:message key="menu" bundle="${rb}"/></a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <c:url value="/profile/user" var="profileShow"/>
                    <li class="nav-item">
                        <a class="nav-link border border-success"
                           href="${profileShow}">${sessionScope.user.login}</a>
                    </li>
                    <c:url value="/user/sign-out" var="userSignOut"/>
                    <li class="nav-item">
                        <a class="nav-link" href="${userSignOut}"><fmt:message key="signOut" bundle="${rb}"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:url value="/user/sign-in" var="userSignIn"/>
                    <a class="nav-link" href="${userSignIn}"><fmt:message key="signIn" bundle="${rb}"/></a>
                    <c:url value="/user/sign-up" var="userSignUp"/>
                    <a class="nav-link" href="${userSignUp}"><fmt:message key="signUp" bundle="${rb}"/></a>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <c:url value="/user/list" var="users"/>
                <a class="nav-link" href="${users}"><fmt:message key="abilities" bundle="${rb}"/></a>
            </c:if>
            <c:url value="uri=${requestScope['javax.servlet.forward.request_uri']}" var="uri"/>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="contacts" data-toggle="dropdown">
                    <fmt:message key="contacts" bundle="${rb}"/>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="mailto:mickeyspizza@gmail.com">mickeyspizza@gmail.com</a>
                    <a class="dropdown-item" href="tel:123-456-7890">123-456-7890</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="l10n" data-toggle="dropdown">
                    <em class='fas fa-globe-americas'></em>
                </a>
                <div class="dropdown-menu">
                    <c:url value="/local?lang=en_US&${uri}" var="en"/>
                    <a class="dropdown-item" href="${en}">EN</a>
                    <c:url value="/local?lang=ru_RU&${uri}" var="ru"/>
                    <a class="dropdown-item" href="${ru}">RU</a>
                    <c:url value="/local?lang=be_BY&${uri}" var="be"/>
                    <a class="dropdown-item" href="${be}">BE</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
