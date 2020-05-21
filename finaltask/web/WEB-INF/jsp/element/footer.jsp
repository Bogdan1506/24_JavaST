<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<div class="container">
    <div class="alert alert-success alert-dismissible mt-5">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong><fmt:message key="${requestScope.message}" bundle="${rb}"/></strong>
    </div>
</div>

