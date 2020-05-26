<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="signUpForm" bundle="${rb}"/></title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<div class="container">
    <p class="display-4 mt-5"><fmt:message key="signUpForm" bundle="${rb}"/></p>
    <c:url value="/user/register" var="registerUser"/>
    <form action="${registerUser}" class="was-validated" name="register" method="post">
        <div class="form-group">
            <label for="login"><fmt:message key="login" bundle="${rb}"/></label>
            <input type="text" class="form-control" id="login"
                   placeholder="<fmt:message key="registerLogin" bundle="${rb}"/>"
                   name="login" pattern="\w{1,255}" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.login}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.login}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
        <div class="form-group">
            <label for="pwd"><fmt:message key="password" bundle="${rb}"/></label>
            <input type="password" class="form-control" id="pwd"
                   placeholder="<fmt:message key="registerPassword" bundle="${rb}"/>" name="password"
                   pattern="\w{5,}" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.password}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.password}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="reppwd"><fmt:message key="repPassword" bundle="${rb}"/></label>
            <input type="password" class="form-control" id="reppwd"
                   placeholder="<fmt:message key="repPassword" bundle="${rb}"/>" name="repPassword"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/>.</div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.repPassword}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.repPassword}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="submit" bundle="${rb}"/></button>
        <c:url value="/user/register" var="registerUser"/>
    </form>
    <c:url value="/user/sign-in" var="userSignIn"/>
    <c:if test="${not empty requestScope.message}">
        <c:import url="../element/footer.jsp"/>
    </c:if>
</div>
</body>
</html>
<script>
    const password = document.getElementById("pwd")
        , confirm_password = document.getElementById("reppwd");

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
