<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-success navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" href="#">pZa</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link text-warning" href="#">Menu</a>
        </li>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="/pza?action=profileUserShow"> ${sessionScope.user.login}</a>
                </li>
                <li>
                    <a class="nav-link" href="/pza?action=userSignOut"> Sign out</a>
                </li>

            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="jsp/user/sign-in.jsp"> Sign in</a>
                </li>
            </c:otherwise>
        </c:choose>
        <li class="nav-item">
            <a class="nav-link" href="#"> Contact</a>
        </li>
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                Lang
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">EN</a>
                <a class="dropdown-item" href="#">RU</a>
<%--                <a class="dropdown-item" href="#">Link 3</a>--%>
            </div>
        </li>
    </ul>
</nav>
<br>

<div class="container">
   <%-- <h3>Navbar With Dropdown</h3>
    <p>This example adds a dropdown menu in the navbar.</p>--%>
</div>

</body>
</html>