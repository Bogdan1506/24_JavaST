<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <%--<script
            src="http://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>--%>

</head>
<body>
<link rel="icon" href="data:,">

<div class="container-fluid">
    <div class="jumbotron text-center p-3 my-3 bg-light text-black" style="margin-bottom:0">
        <h1 class="display-1">Pizza</h1>
        <%--        <img src="img/pic.jpg" class="rounded-circle" alt="Pizza bar" width="304" height="236">--%>
    </div>
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