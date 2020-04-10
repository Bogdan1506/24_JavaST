<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile update</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../bar.jsp"/>
<div class="container">
    <h2>Profile Update Form</h2>
    <c:url value="/profile/update" var="profileUpdate"/>
    <form action="${profileUpdate}" class="form-group" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"
                   value="${profile.name}" required>
        </div>
        <div class="form-group">
            <label for="surname">Surname:</label>
            <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname"
                   value="${profile.surname}" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email"
                   value="${profile.email}">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone"
                   value="${profile.phone}" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" placeholder="Enter address" name="address"
                   value="${profile.address}" required>
        </div>
        <input type="hidden" name="userId" value="${user.id}">
        <button type="submit" class="btn btn-success">Save</button>
    </form>
    <br/>
    <h3>Password</h3>
    <c:url value="/user/update" var="userUpdate"/>
    <form action="${userUpdate}" class="form-group" method="post">
        <div class="form-group">
            <label for="oldPassword">Current Password:</label>
            <input type="password" class="form-control" id="oldPassword" placeholder="Enter your current password"
                   name="oldPassword">
        </div>
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" class="form-control" id="newPassword" placeholder="Enter new password"
                   name="newPassword">
        </div>
        <button type="submit" class="btn btn-danger">Change</button>
    </form>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
<script>
    var password = document.getElementById("oldPassword")
        , confirm_password = document.getElementById("newPassword");

    function validatePassword() {
        if (password.value == confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>