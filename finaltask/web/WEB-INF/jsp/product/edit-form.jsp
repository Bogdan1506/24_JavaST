<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="menu-bar.jsp"/>
<div class="container">
    <p style="text-align: center" class="display-4">Product form</p>
    <c:url var="order" value="/product/edit"/>
    <form action="${order}" method="post">
        <input type="hidden" name="id" value="${requestScope.product.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control mb-3" id="name" name="name" value="${requestScope.product.name}"
                   required>
            <div class="form-group">
                <label for="desc">Description:</label>
                <input type="text" class="form-control mb-3" id="desc" name="description"
                       value="${requestScope.product.description}" required>
            </div>
            <label for="price">Price:</label><input class="form-control mb-3" type="text" id="price" name="price"
                                                    value="${requestScope.product.price}" required/>
            <label for="pic">Picture:</label>
            <input class="form-control mb-3" type="text" id="pic" name="picture"
                   value="${requestScope.product.picture}"/>
            <label for="type">Type:</label>
            <select id="type" name="type" class="custom-select">
                <c:choose>
                    <c:when test="${requestScope.product.type eq 'PIZZA'}">
                        <option value="pizza">Pizza</option>
                        <option value="sides">Sides</option>
                        <option value="drinks">Drinks</option>
                    </c:when>
                    <c:when test="${requestScope.product.type eq 'SIDES'}">
                        <option value="sides">Sides</option>
                        <option value="pizza">Pizza</option>
                        <option value="drinks">Drinks</option>
                    </c:when>
                    <c:otherwise>
                        <option value="drinks">Drinks</option>
                        <option value="pizza">Pizza</option>
                        <option value="sides">Sides</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <button type="submit" class="btn btn-primary float-right mt-3">Edit</button>
        </div>
    </form>
    <%--<form action="/product/edit" enctype="multipart/form-data">
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
