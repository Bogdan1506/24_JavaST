<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
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
    <p class="display-4 mt-5"><fmt:message key="profileCreateForm" bundle="${rb}"/></p>
    <c:url value="/profile/register" var="profileCreate"/>
    <form action="${profileCreate}" class="was-validated" method="post">
        <div class="form-group">
            <label for="name"><fmt:message key="name" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="name"
                   placeholder="<fmt:message key="enterName" bundle="${rb}"/>" name="name" pattern="[a-zA-z]+"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.name}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.name}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="surname"><fmt:message key="surname" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="surname"
                   placeholder="<fmt:message key="enterSurname" bundle="${rb}"/>" name="surname"
                   pattern="[a-zA-z]+" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.surname}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.surname}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="email"><fmt:message key="email" bundle="${rb}"/>:</label>
            <input type="email" class="form-control" id="email"
                   placeholder="<fmt:message key="enterEmail" bundle="${rb}"/>"
                   name="email" pattern="\w+@\w+\.\w+">
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.email}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.email}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="phone"><fmt:message key="phone" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="phone" placeholder="<fmt:message key="enterPhone" bundle="${rb}"/>"
                   name="phone"
                   pattern="\d+" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.phone}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.phone}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="address"><fmt:message key="address" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="address"
                   placeholder="<fmt:message key="enterAddress" bundle="${rb}"/>" name="address"
                   pattern="\w+" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.phone}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.phone}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <button type="submit" class="btn btn-success"><fmt:message key="submit" bundle="${rb}"/></button>
    </form>
    <c:if test="${not empty requestScope.message}">
        <c:import url="../element/footer.jsp"/>
    </c:if>
</div>
</body>
</html>
