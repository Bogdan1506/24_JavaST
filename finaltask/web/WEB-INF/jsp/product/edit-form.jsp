<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="menu-bar.jsp"/>
<div class="container mb-5">
    <p style="text-align: center" class="display-4"><fmt:message key="productEditForm" bundle="${rb}"/></p>
    <c:url var="order" value="/product/edit"/>
    <form action="${order}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${requestScope.product.id}">
        <div class="form-group">
            <label for="name"><fmt:message key="name" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="name"
                   placeholder="<fmt:message key="enterName" bundle="${rb}"/>"
                   name="name" pattern="\w+" value="${requestScope.product.name}" required>
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
            <label for="description"><fmt:message key="desc" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="description"
                   placeholder="<fmt:message key="enterDesc" bundle="${rb}"/>" name="description"
                   value="${requestScope.product.description}" required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <fmt:message key="fillOut" bundle="${rb}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="price"><fmt:message key="price" bundle="${rb}"/>:</label>
            <input type="text" class="form-control" id="price"
                   placeholder="<fmt:message key="enterPrice" bundle="${rb}"/>" name="price"
                   value="${requestScope.product.price}" pattern="\d+\.?\d{1,2}"
                   required>
            <div class="valid-feedback"><fmt:message key="valid" bundle="${rb}"/></div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.price}">
                        <fmt:message key="fillOut" bundle="${rb}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${requestScope.param.price}" bundle="${rb}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label>
                <label for="picture"><fmt:message key="picture" bundle="${rb}"/>(.png, .jpg):
                    <input type="file" id="picture" name="picture" size="50">
        </div>
        <label for="type">Type:</label>
        <select id="type" name="type" class="custom-select">
            <c:choose>
                <c:when test="${requestScope.product.type eq 'PIZZA'}">
                    <option value="pizza"><fmt:message key="pizzas" bundle="${rb}"/></option>
                    <option value="sides"><fmt:message key="sides" bundle="${rb}"/></option>
                    <option value="drink"><fmt:message key="drinks" bundle="${rb}"/></option>
                </c:when>
                <c:when test="${requestScope.product.type eq 'SIDES'}">
                    <option value="sides"><fmt:message key="sides" bundle="${rb}"/></option>
                    <option value="pizza"><fmt:message key="pizzas" bundle="${rb}"/></option>
                    <option value="drink"><fmt:message key="drinks" bundle="${rb}"/></option>
                </c:when>
                <c:otherwise>
                    <option value="drink"><fmt:message key="drinks" bundle="${rb}"/></option>
                    <option value="pizza"><fmt:message key="pizzas" bundle="${rb}"/></option>
                    <option value="sides"><fmt:message key="sides" bundle="${rb}"/></option>
                </c:otherwise>
            </c:choose>
        </select>
        <button type="submit" class="btn btn-primary float-right m-3"><fmt:message key="edit" bundle="${rb}"/></button>
    </form>
</div>
<c:if test="${not empty message}">
    <c:import url="../element/footer.jsp"/>
</c:if>
</body>
</html>
