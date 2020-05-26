<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart</title>
</head>
<body>
<div id="cart" class="sticky-top">
    <p style="text-align: center;" class="display-4 pt-5 mt-5"><fmt:message key="cart" bundle="${rb}"/></p>
    <c:choose>
    <c:when test="${empty sessionScope.cart}">
        <p style="color: red; text-align: right"><fmt:message key="emptyCart" bundle="${rb}"/></p>
    </c:when>
    <c:otherwise>
    <p>
        <strong>
            <button class="btn btn-outline-danger" onclick="remove(`all`)"><fmt:message key="reset" bundle="${rb}"/>
                <span class="badge">${sessionScope.cart.size()}</span></button>
        </strong>
    </p>
    <div class="overflow-auto p-3 mb-3 mb-md-0 mr-md-3"
         style="max-width: 500px; max-height: 500px; background-color: white">
        </c:otherwise>
        </c:choose>
        <c:forEach var="temp" items="${sessionScope.cart}">
            <ul style="list-style-type:none;padding: 0;
  width: 200px;
  margin: 0 auto;">
                <li><img alt="" src="/img/${temp.product.picture}" width="60" height="60"></li>
                <li>
                    <strong>
                        <c:out value="${temp.product.name}"/>
                    </strong>
                    <c:if test="${not empty temp.dough}">
                <li><fmt:message key="${temp.dough}" bundle="${rb}"/></li>
                </c:if>
                <li><fmt:message key="${temp.size}" bundle="${rb}"/></li>
                <li><strong><em><fmt:formatNumber type="number" pattern="##.##"
                                                  value="${temp.product.price * temp.size.coefficient}"/>
                    BYN</em></strong></li>
                <li>
                    <label>
                        <input class="btn btn-sm btn-outline-danger" onclick="remove(${temp.id})" value="x">
                    </label>
                </li>
            </ul>
        </c:forEach>
    </div>
    <c:if test="${not empty sessionScope.cart}">
        <c:url value="/delivery/form" var="createItems"/>
        <form action="${createItems}">
            <input class="btn btn-lg btn-warning" type="submit" value="<fmt:message key="order" bundle="${rb}"/>">
            <strong><fmt:formatNumber type="number" pattern="##.##" value="${sessionScope.totalPrice}"/> BYN</strong>
        </form>
    </c:if>
</div>
</body>
</html>
<script>
    function remove(value) {
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("cart").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "/item/cart/remove?id=" + value, true);
        xhttp.send();
    }
</script>
