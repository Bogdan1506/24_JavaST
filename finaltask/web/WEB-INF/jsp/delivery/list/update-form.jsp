<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="deliveryForm" bundle="${rb}"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<c:import url="../../element/navbar.jsp"/>
<c:import url="../../element/admin-bar.jsp"/>
<p style="text-align: center" class="display-4"><fmt:message key="deliveryForm" bundle="${rb}"/></p>
<div class="container">
    <c:url var="update" value="/delivery/list/update"/>
    <form action="${update}" class="was-validated" method="post">
        <input type="hidden" name="id" value="${requestScope.delivery.id}">
        <label for="date"><fmt:message key="date" bundle="${rb}"/>:</label>
        <div class="form-group">
            <input type="datetime-local" id="date"
                   name="date" value="${requestScope.date}"
                   min="${requestScope.minDate}" max="2038-01-01T00:00" autofocus/>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <fmt:message key="invalidDeliver" bundle="${rb}"/>
            </div>
        </div>
        <label>Payment:
            <input type="radio" name="payment" id="cash" value="Cash" autocomplete="on" checked="checked">
            <label for="cash"><fmt:message key="cash" bundle="${rb}"/></label>
            <input type="radio" name="payment" id="card" value="Card">
            <label for="card"><fmt:message key="card" bundle="${rb}"/></label>
        </label>
        <button type="submit" class="btn btn-warning float-right mt-3"><fmt:message key="update"
                                                                                    bundle="${rb}"/></button>
    </form>
</div>
<c:if test="${not empty message}">
    <c:import url="../../element/footer.jsp"/>
</c:if>
</body>
</html>
