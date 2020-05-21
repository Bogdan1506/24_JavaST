<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="signInForm" bundle="${rb}"/></title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<div class="container">
    <p class="display-4 mt-5"><fmt:message key="signInForm" bundle="${rb}"/></p>
    <c:url value="/user/login" var="signIn"/>
    <form action="${signIn}" class="was-validated" name="signIn" method="post">
        <div class="form-group">
            <label for="login"><fmt:message key="login" bundle="${rb}"/></label>
            <input type="text" class="form-control" id="login"
                   placeholder="<fmt:message key="enterLogin" bundle="${rb}"/>" name="login" autofocus autocomplete="on"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback"><fmt:message key="fillOut" bundle="${rb}"/></div>
        </div>
        <div class="form-group">
            <label for="pwd"><fmt:message key="password" bundle="${rb}"/></label>
            <input type="password" class="form-control" id="pwd"
                   placeholder="<fmt:message key="enterPassword" bundle="${rb}"/>" name="password"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback"><fmt:message key="fillOut" bundle="${rb}"/></div>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="submit" bundle="${rb}"/></button>
    </form>
    <c:url value="/user/sign-up" var="signUp"/>
    <a href="${signUp}"><fmt:message key="signUp" bundle="${rb}"/></a>
    <c:url value="/" var="menu"/>
</div>
<c:if test="${not empty requestScope.message}">
    <c:import url="../element/footer.jsp"/>
</c:if>
</body>
</html>
