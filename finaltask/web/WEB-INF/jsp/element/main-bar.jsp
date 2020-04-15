<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>--%>



    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>
    <script
            src="http://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>



    <link rel="stylesheet" type="text/css" href="../../static/css/bar.css">

</head>
<body>
<link rel="icon" href="data:,">

    <div class="header">
        <img src="../../static/img/header.jpg" alt="">
    </div>
<%--<div class="container-fluid">
    <div class="jumbotron text-center p-3 my-3 bg-light text-black" style="margin-bottom:0">
        <h1 class="display-1">Pizza</h1>
                <img src="static/img/pic.png" class="rounded-circle" alt="Pizza bar" width="304" height="236">
    </div>--%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <a class="navbar-brand" href="#">Pizza</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <c:url value="/" var="menu"/>
                <a class="nav-link text-warning" href="${menu}">Menu</a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <c:url value="/profile/user" var="profileShow"/>
                        <a class="nav-link" href="${profileShow}"> ${sessionScope.user.login}</a>
                    </li>
                    <li>
                        <c:url value="/user/sign-out" var="userSignOut"/>
                        <a class="nav-link" href="${userSignOut}">Sign out</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <c:url value="/user/sign-in" var="userSignIn"/>
                        <a class="nav-link" href="${userSignIn}"> Sign in</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <li class="nav-item">
                    <c:url value="/user/list" var="users"/>
                    <a class="nav-link" href="${users}">Users</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="#"> Contact</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Lang
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">EN</a>
                    <a class="dropdown-item" href="#">RU</a>
                </div>
            </li>

        </ul>
    </nav>
    <br>
</div>
</body>
</html>