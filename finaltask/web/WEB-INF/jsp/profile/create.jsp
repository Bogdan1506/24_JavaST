<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <p class="display-4 mt-5">Profile</p>
    <c:url value="/profile/register" var="profileCreate"/>
    <form action="${profileCreate}" class="was-validated" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" pattern="[a-zA-z]+" required>
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
            <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname" pattern="[a-zA-z]+" required>
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
            <input type="text" class="form-control" id="email" placeholder="Enter email (example@gmail.com)"
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
            <input type="text" class="form-control" id="phone" placeholder="Enter phone (+1234567)" name="phone" pattern="\+\d+" required>
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
            <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" pattern="\w+" required>
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
</div>
</body>
</html>
