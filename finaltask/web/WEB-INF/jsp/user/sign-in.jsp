<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Sign in form</h2>
    <c:url value="/user/login" var="signIn"/>
    <form action="${signIn}" class="was-validated" name="signIn" method="post">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password"
                   required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember"> Save me.
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <c:url value="/user/sign-up" var="signUp"/>
    <a href="${signUp}">Sign up</a>
    <c:url value="/" var="menu"/>
    <form action="${menu}">
        <button type="submit" class="btn btn-warning float-right">Cancel</button>
    </form>
    <c:if test="${not empty message}">
    <div class="alert alert-danger alert-dismissible mt-5">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${message}</strong>
    </div>
    </c:if>
</body>
</html>
