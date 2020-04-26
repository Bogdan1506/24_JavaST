<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delivery List</title>
</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../element/admin-bar.jsp"/>
<div class="container mt-3">
    <p style="text-align: center" class="display-4">Delivery list</p>
    <input class="form-control" id="searchInput" type="text" placeholder="Search" aria-label="Search">
    <br/>
    <table class="table table-bordered" id="deliveryTable">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">orderPosition</th>
            <th scope="row">date</th>
            <th scope="row">payment</th>
            <th scope="row">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${requestScope.deliveries}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.orderPosition.id}"/></td>
                <td><c:out value="${temp.date}"/></td>
                <td><c:out value="${temp.payment}"/></td>
                <td>
                    <c:url value="/delivery/list/remove" var="deliveryDelete"/>
                    <form action="${deliveryDelete}" method="post">
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
        <c:url var="pagePreviousUrl" value="/delivery/list?page=${pagePrevious}"/>
        <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
        <c:set value="${requestScope.page}" var="page1"/>
        <c:url var="page1url" value="/delivery/list?page=${page1}"/>
        <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
        <c:set value="${requestScope.page + 1}" var="page2"/>
        <c:url var="page2url" value="/delivery/list?page=${page2}"/>
        <li class="page-item"><a class="page-link" href="${page2url}">${page2}</a></li>
        <c:set value="${requestScope.page + 2}" var="page3"/>
        <c:url var="page3url" value="/delivery/list?page=${page3}"/>
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
            $("#deliveryTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
