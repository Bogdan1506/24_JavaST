<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../product/menu-bar.jsp"/>
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
    <c:url var="saveProduct" value="/product/edit"/>
    <form action="${saveProduct}" method="post">
        <input type="hidden" name="id" value="${requestScope.product.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control mb-3" id="name" name="name" value="${requestScope.profile.name}"
                   required>
            <label for="surname">Surname:</label>
            <input type="text" class="form-control mb-3" id="surname" name="surname"
                   value="${requestScope.profile.surname}"
                   required>
            <label for="phone">Phone number:</label>
            <input type="text" class="form-control mb-3" id="phone" name="phone" value="${requestScope.profile.phone}"
                   required>
            <label for="address">Address:</label>
            <input type="text" class="form-control mb-3" id="address" name="address"
                   value="${requestScope.profile.address}"
                   required>
            <div class="form-group row">
                <label for="example-date-input" class="col-2 col-form-label">Date:</label>
                <div class="col-10">
                    <input class="form-control" type="date" id="example-date-input">
                    <%--                    <input class="form-control" type="date" value="2011-08-19" id="example-date-input">--%>
                </div>

            </div>
        </div>
        <label>Payment:
            <input type="radio" name="payment" id="cash" value="Cash">
            <label for="cash">Cash</label>
            <input type="radio" name="payment" id="card" value="Card">
            <label for="card">Card</label>
        </label>
        <button type="submit" class="btn btn-warning float-right mt-3">Order</button>
    </form>
    <%-- <form action="/product/edit" enctype="multipart/form-data">
         <div class="form-group">
             <input type="file" class="form-control-file border mb-3" name="file">
         </div>
         <button type="submit" class="btn btn-success">Upload</button>
     </form>--%>
    <c:if test="${not empty message}">
        <jsp:include page="../element/footer.jsp"/>
    </c:if>
</div>
</body>
</html>
