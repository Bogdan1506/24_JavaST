<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart</title>
</head>
<body>
<div class="container">
    <h3>Cart</h3>
    <table id="tab" class="table table-borderless" style="height: 100%;">
        <tbody>
        <c:forEach var="temp" items="${cart}">
            <tr>
                <td>
                    <p>
                        <img alt="" src="${temp.product.picture}" width="45" height="45">
                    </p>
                    <h3>
                        <c:out value="${temp.product.name}"/>
                    </h3>
                    <p>${temp.dough}</p>
                    <p>${temp.size}</p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="#">
        <input class="btn btn-warning" type="submit" value="Order">
    </form>
</div>
</body>
</html>
