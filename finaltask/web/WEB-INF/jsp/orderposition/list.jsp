<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order Position List</title>
</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../element/admin-bar.jsp"/>
<div class="container mt-3">
    <p style="text-align: center" class="display-4">Order Position list</p>
    <input class="form-control" id="searchInput" type="text" placeholder="Search" aria-label="Search">
    <br/>
    <table class="table table-bordered" id="orderPosTable">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">item</th>
            <th scope="row">order</th>
            <th scope="row">price</th>
            <th scope="row">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${requestScope.orderPositions}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.item.id}"/></td>
                <td><c:out value="${temp.order.id}"/></td>
                <td><c:out value="${temp.price}"/></td>
                <td>
                    <c:url value="/orderposition/list/remove" var="orderPositionDelete"/>
                    <form action="${orderPositionDelete}" method="post">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination justify-content-center">
        <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
        <c:url var="pagePreviousUrl" value="/orderposition/list?page=${pagePrevious}"/>
        <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
        <c:set value="${requestScope.page}" var="page1"/>
        <c:url var="page1url" value="/orderposition/list?page=${page1}"/>
        <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
        <c:set value="${requestScope.page + 1}" var="page2"/>
        <c:url var="page2url" value="/orderposition/list?page=${page2}"/>
        <li class="page-item"><a class="page-link" href="${page2url}">${page2}</a></li>
        <c:set value="${requestScope.page + 2}" var="page3"/>
        <c:url var="page3url" value="/orderposition/list?page=${page3}"/>
        <li class="page-item"><a class="page-link" href="${page3url}">${page3}</a></li>
        <li class="page-item"><a class="page-link" href="${page2url}">Next</a></li>
    </ul>
    <c:if test="${not empty requestScope.message}">
        <jsp:include page="../element/footer.jsp"/>
    </c:if>
</div>
<script>
    $(document).ready(function () {
        $("#searchInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#orderPosTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
