<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile update</title>
    <meta charset="utf-8">
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<div class="container">
    <p class="display-4">Profile Update Form</p>
    <c:url value="/profile/update" var="profileUpdate"/>
        <form action="${profileUpdate}" class="was-validated" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${requestScope.profile.name}" pattern="[a-zA-z]+" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    <c:choose>
                        <c:when test="${empty requestScope.param.name}">
                            Please fill out this field.
                        </c:when>
                        <c:otherwise>
                            ${requestScope.param.name}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname" value="${requestScope.profile.surname}"  pattern="[a-zA-z]+" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    <c:choose>
                        <c:when test="${empty requestScope.param.surname}">
                            Please fill out this field.
                        </c:when>
                        <c:otherwise>
                            ${requestScope.param.surname}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email"  value="${requestScope.profile.email}" placeholder="Enter email (example@gmail.com)"
                       name="email" pattern="\w+@\w+\.\w+">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    <c:choose>
                        <c:when test="${empty requestScope.param.email}">
                            Please fill out this field.
                        </c:when>
                        <c:otherwise>
                            ${requestScope.param.email}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" placeholder="Enter phone (+1234567)" name="phone" value="${requestScope.profile.phone}" pattern="\+\d+" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    <c:choose>
                        <c:when test="${empty requestScope.param.phone}">
                            Please fill out this field.
                        </c:when>
                        <c:otherwise>
                            ${requestScope.param.phone}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" value="${requestScope.profile.address}" pattern="\w+" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    <c:choose>
                        <c:when test="${empty requestScope.param.phone}">
                            Please fill out this field.
                        </c:when>
                        <c:otherwise>
                            ${requestScope.param.phone}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
    <br/>
    <p class="display-4">Password</p>
    <c:url value="/user/update" var="userUpdate"/>
    <form action="${userUpdate}" class="was-validated" method="post">
        <div class="form-group">
            <label for="oldPassword">Current password:</label>
            <input type="password" class="form-control" id="oldPassword" placeholder="Enter current password"
                   name="oldPassword" pattern="\w{5,30}" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                Please fill out this field.
            </div>
        </div>
        <div class="form-group">
            <label for="newPassword">New password:</label>
            <input type="password" class="form-control" id="newPassword"
                   placeholder="Enter password (only letters and digits — minimum 5, max — 30)" name="newPassword"
                   pattern="\w{5,30}" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.newPassword}">
                        Please fill out this field.
                    </c:when>
                    <c:otherwise>
                        ${requestScope.param.newPassword}
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <button type="submit" class="btn btn-danger">Change</button>
    </form>
</div>
<c:if test="${not empty requestScope.message}">
    <c:import url="../element/footer.jsp"/>
</c:if>
</body>
</html>
<script>
    const password = document.getElementById("oldPassword")
        , confirm_password = document.getElementById("newPassword");

    function validatePassword() {
        if (password.value === confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>