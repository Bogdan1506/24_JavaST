<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="profileUpdateForm" bundle="${rb}"/></title>
    <meta charset="utf-8">
</head>
<body>
<c:import url="../../element/navbar.jsp"/>
<c:import url="../../element/admin-bar.jsp"/>
<div class="container">
    <p class="display-4"><fmt:message key="profileUpdateForm" bundle="${rb}"/></p>
    <c:url value="/order/list/update" var="profileUpdate"/>
    <form action="${profileUpdate}" class="was-validated" method="get">
        <div class="form-group">
            <label for="name"><fmt:message key="name" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="name"
                   placeholder="<fmt:message key="enterName" bundle="${rb}"/>" name="name" pattern="[a-zA-z]+"
                   value="${requestScope.profile.name}"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty param.name}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${param.name}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="surname"><fmt:message key="surname" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="surname"
                   placeholder="<fmt:message key="enterSurname" bundle="${rb}"/>" name="surname"
                   value="${requestScope.profile.surname}"
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
                   placeholder="<fmt:message key="enterEmail" bundle="${rb}"/>" value="${requestScope.profile.email}"
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
            <input type="text" class="form-control" id="phone" placeholder="<fmt:message key="phone" bundle="${rb}"/>"
                   name="phone" value="${requestScope.profile.phone}"
                   pattern="\+\d+" required>
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
                   value="${requestScope.profile.address}"
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
        <button type="submit" class="btn btn-success"><fmt:message key="update" bundle="${rb}"/></button>
    </form>
</div>
<c:if test="${not empty requestScope.message}">
    <c:import url="../../element/footer.jsp"/>
</c:if>
</body>
</html>