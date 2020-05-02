<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile update</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<div class="container">
    <p class="display-4">Profile Update Form</p>
    <c:url value="/profile/update" var="profileUpdate"/>
    <form action="${profileUpdate}" class="form-group" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"
                   value="${requestScope.profile.name}" required>
        </div>
        <div class="form-group">
            <label for="surname">Surname:</label>
            <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname"
                   value="${requestScope.profile.surname}" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email"
                   value="${requestScope.profile.email}">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone"
                   value="${requestScope.profile.phone}" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" placeholder="Enter address" name="address"
                   value="${requestScope.profile.address}" required>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
    <br/>
    <p class="display-4">Password</p>
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
<c:if test="${not empty requestScope.message}">
    <jsp:include page="../element/footer.jsp"/>
</c:if>
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