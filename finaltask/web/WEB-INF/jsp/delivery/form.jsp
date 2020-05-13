<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delivery form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="../product/menu-bar.jsp"/>
<p style="text-align: center" class="display-4">Delivery form</p>
<div class="container">
    <div class="overflow-auto p-3 mb-3 mb-md-0 mr-md-3 bg-light" style="max-width: 1200px; max-height: 300px;">
        <c:forEach var="temp" items="${sessionScope.cart}">
            <p>
                <img alt="" src="${temp.product.picture}" width="60" height="60">
                <c:out value="${temp.product.name}"/>
                <c:out value="${temp.dough}"/>
                <c:out value="${temp.size}"/>
            </p>
        </c:forEach>
    </div>
    <c:url var="order" value="/delivery/order"/>
    <form action="${order}" class="was-validated" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"
                   value="${requestScope.profile.name}" pattern="[a-zA-z]+" required>
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
            <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname"
                   value="${requestScope.profile.surname}" pattern="[a-zA-z]+" required>
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
            <input type="text" class="form-control" id="email" value="${requestScope.profile.email}"
                   placeholder="Enter email (example@gmail.com)"
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
            <input type="text" class="form-control" id="phone" placeholder="Enter phone (+1234567)" name="phone"
                   value="${requestScope.profile.phone}" pattern="\+\d+" required>
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
            <input type="text" class="form-control" id="address" placeholder="Enter address" name="address"
                   value="${requestScope.profile.address}" pattern="\w+" required>
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
            <label for="date">Date:</label>
            <div class="form-group">
                <input type="datetime-local" id="date"
                       name="date" value="${requestScope.date}"
                       min="${requestScope.date}"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">
                    Cannot deliver on this date.
                </div>
            </div>
            <input type="hidden" name="id" value="${requestScope.product.id}">
            <label>Payment:
                <input type="radio" name="payment" id="cash" value="Cash" autocomplete="on" checked="checked">
                <label for="cash">Cash</label>
                <input type="radio" name="payment" id="card" value="Card">
                <label for="card">Card</label>
            </label>
            <button type="submit" class="btn btn-warning float-right mt-3">Order</button>
        </div>
    </form>
    <c:if test="${not empty message}">
        <c:import url="../element/footer.jsp"/>
    </c:if>
</div>
</body>
</html>
