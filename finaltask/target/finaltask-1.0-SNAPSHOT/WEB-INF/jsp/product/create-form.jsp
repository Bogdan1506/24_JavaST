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
    <c:url var="order" value="/product/create"/>
    <form action="${order}" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control mb-3" id="name" name="name"
                   required>
            <div class="form-group">
                <label for="desc">Description:</label>
                <input type="text" class="form-control mb-3" id="desc" name="description" required>
            </div>
            <label for="price">Price:</label><input class="form-control mb-3" type="text" id="price" name="price" required/>
            <label for="pic">Picture:</label>
            <input class="form-control mb-3" type="text" id="pic" name="picture"/>
            <label for="type">Type:</label>
            <select id="type" name="type" class="custom-select">
                        <option value="pizza">Pizza</option>
                        <option value="sides">Sides</option>
                        <option value="drinks">Drinks</option>
            </select>
            <button type="submit" class="btn btn-primary float-right mt-3">Save</button>
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
