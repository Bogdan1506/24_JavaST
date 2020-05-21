<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Menu</title>
    <script src="../../../js/remove.js"></script>
    <script src="../../../js/add-to-cart.js"></script>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="menu-bar.jsp"/>
<div class="container mt-6">
    <div class="row">
        <div class="col-sm-9">
            <div class="row">
                <c:forEach var="temp" items="${requestScope.products}">
                    <div class="col-sm-4 mt-5">
                        <p>
                            <img alt="Pizza image" src="../../../img/${temp.picture}" width="280"
                                 height="280">
                        </p>
                        <h3>
                            <c:out value="${temp.name}"/>
                        </h3>
                        <p>
                            <button type="button" class="btn btn-light" data-toggle="tooltip" data-placement="right"
                                    title="${temp.description}">
                                <fmt:message key="ingredients" bundle="${rb}"/>
                            </button>
                        </p>
                        <c:url value="/item/cart" var="addToCart"/>
                        <c:if test="${sessionScope.user.role ne 'CREATOR'}">
                            <c:if test="${temp.type eq 'PIZZA'}">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-success active">
                                        <input class="dough_param" type="radio" name="dough" id="thick"
                                               value="Thick"
                                               autocomplete="off"
                                               checked>
                                        <fmt:message key="thick" bundle="${rb}"/>
                                    </label>
                                    <label class="btn btn-success">
                                        <input class="dough_param" type="radio" name="dough" id="thin" value="Thin"
                                               autocomplete="off">
                                        <fmt:message key="thin" bundle="${rb}"/>
                                    </label>
                                </div>
                            </c:if>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                <label class="btn btn-primary active">
                                    <input class="size_param" type="radio" name="size" id="size1" value="Small"
                                           autocomplete="off"
                                           checked>
                                    S ${temp.price}
                                </label>
                                <label class="btn btn-primary">
                                    <c:set var="amountM" value="${temp.price * 2}"/>
                                    <input class="size_param" type="radio" name="size" id="size2" value="Medium"
                                           autocomplete="off"> M
                                    <fmt:formatNumber type="number" pattern="##.#" value="${amountM}"/>
                                </label>
                                <c:set var="amountL" value="${temp.price * 3}"/>
                                <label class="btn btn-primary">
                                    <input class="size_param" type="radio" name="size" id="size3" value="Large"
                                           autocomplete="off">
                                    L
                                    <fmt:formatNumber type="number" pattern="##.#" value="${amountL}"/>
                                </label>
                            </div>
                            <input type="submit" class="btn btn-light"
                                   value="<fmt:message key="addToCart" bundle="${rb}"/>"
                                   onclick="addToCart(${temp.id})">
                        </c:if>
                        <c:if test="${sessionScope.user.role eq 'CREATOR'}">
                            <c:url var="removePosition" value="/product/remove"/>
                            <c:url var="editPosition" value="/product/edit-form"/>
                            <div class="btn-group">
                                <form action="${removePosition}" method="post">
                                    <input type="hidden" name="id" value="${temp.id}">
                                    <button type="submit" class="btn btn-danger btn-lg"><fmt:message key="remove" bundle="${rb}"/></button>
                                </form>
                                <form action="${editPosition}" method="post">
                                    <input type="hidden" name="id" value="${temp.id}">
                                    <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="edit" bundle="${rb}"/></button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-3 mt-5">
            <c:if test="${sessionScope.user.role eq 'CREATOR'}">
                <c:url var="addPosition" value="/product/create-form"/>
                <form action="${addPosition}">
                    <button class="btn btn-warning btn-lg"><fmt:message key="add" bundle="${rb}"/></button>
                </form>
            </c:if>
            <c:import url="../item/cart.jsp"/>
        </div>
    </div>
    <c:if test="${not empty requestScope.message}">
        <c:import url="../element/footer.jsp"/>
    </c:if>
</div>
</body>
</html>
