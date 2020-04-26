<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order List</title>
</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../element/admin-bar.jsp"/>
<div class="container mt-3">
    <p style="text-align: center" class="display-4">Order list</p>
    <input class="form-control" id="searchInput" type="text" placeholder="Search" aria-label="Search">
    <br/>
    <table class="table table-bordered" id="orderTable">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">profile</th>
            <th scope="row">date</th>
            <th scope="row">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${requestScope.orders}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.profile.id}"/></td>
                <td><c:out value="${temp.date}"/></td>
                <td>
                    <c:url value="/order/list/remove" var="orderDelete"/>
                    <form action="${orderDelete}" method="post">
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
        <c:url var="pagePreviousUrl" value="/order/list?page=${pagePrevious}"/>
        <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
        <c:set value="${requestScope.page}" var="page1"/>
        <c:url var="page1url" value="/order/list?page=${page1}"/>
        <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
        <c:set value="${requestScope.page + 1}" var="page2"/>
        <c:url var="page2url" value="/order/list?page=${page2}"/>
        <li class="page-item"><a class="page-link" href="${page2url}">${page2}</a></li>
        <c:set value="${requestScope.page + 2}" var="page3"/>
        <c:url var="page3url" value="/order/list?page=${page3}"/>
        <li class="page-item"><a class="page-link" href="${page3url}">${page3}</a></li>
        <%--            <c:set value="${requestScope.page + 1}" var="pageNext"/>
                    <c:url var="pageNextUrl" value="/item/items?page=${pageNext}"/>--%>
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
            $("#orderTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
