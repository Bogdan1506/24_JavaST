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
<c:import url="../../element/navbar.jsp"/>
<c:import url="../../product/menu-bar.jsp"/>
<p style="text-align: center" class="display-4">Delivery form</p>
<div class="container">
    <c:url var="update" value="/delivery/list/update"/>
    <form action="${update}" class="was-validated" method="post">
        <input type="hidden" name="id" value="${requestScope.delivery.id}">
        <label for="date">Date:</label>
        <div class="form-group">
            <input type="datetime-local" id="date"
                   name="date" value="${requestScope.delivery.date}"
                   min="${requestScope.date}"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                Cannot deliver on this date.
            </div>
        </div>
        <label>Payment:
            <input type="radio" name="payment" id="cash" value="Cash" autocomplete="on" checked="checked">
            <label for="cash">Cash</label>
            <input type="radio" name="payment" id="card" value="Card">
            <label for="card">Card</label>
        </label>
        <button type="submit" class="btn btn-warning float-right mt-3">Update</button>
    </form>
</div>
<c:if test="${not empty message}">
    <c:import url="../../element/footer.jsp"/>
</c:if>
</body>
</html>
