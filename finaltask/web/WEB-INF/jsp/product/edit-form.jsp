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
<c:import url="../element/navbar.jsp"/>
<c:import url="menu-bar.jsp"/>
<div class="container">
    <p style="text-align: center" class="display-4">Product form</p>
    <c:url var="order" value="/product/edit"/>
    <form action="${order}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${requestScope.product.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" value="${product.name}"
                   placeholder="Enter name (only letters and digits)"
                   name="name" pattern="\w+" required>
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
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description"
                   placeholder="Enter description" name="description" value="${product.description}" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                Please fill out this field.
            </div>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" id="price" placeholder="Enter price" name="price"
                   value="${product.price}"
                   required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                <c:choose>
                    <c:when test="${empty requestScope.param.price}">
                        Please fill out this field.
                    </c:when>
                    <c:otherwise>
                        ${requestScope.param.price}
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label for="picture">Picture:</label>
            <input type="file" id="picture" name="picture" size="50" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                Please fill out this field.
            </div>
        </div>
<%--        <div class="form-group">
            <label for="picture">Picture:</label>
            <input type="file" id="picture" name="picture" size="50" value="${product.picture}" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">
                Please fill out this field.
            </div>
        </div>--%>
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
                    <option value="drink">Drinks</option>
                    <option value="pizza">Pizza</option>
                    <option value="sides">Sides</option>
                </c:otherwise>
            </c:choose>
        </select>
        <button type="submit" class="btn btn-primary float-right mt-3">Edit</button>
    </form>
</div>
<c:if test="${not empty message}">
    <c:import url="../element/footer.jsp"/>
</c:if>
</body>
</html>
