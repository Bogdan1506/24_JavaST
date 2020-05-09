<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Item List</title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="../element/admin-bar.jsp"/>
<div class="container-fluid">
    <div class="row">

        <div class="col-3">
        </div>
        <div class="col-6">
            <p style="text-align: center" class="display-4">Item list</p>
            <input class="form-control" id="searchInput" type="text" placeholder="Search" aria-label="Search">
            <br/>
            <table class="table table-bordered" id="itemTable">
                <thead class="thead-light">
                <tr>
                    <th scope="row">id</th>
                    <th scope="row">name</th>
                    <th scope="row">size</th>
                    <th scope="row">dough</th>
                    <th scope="row">delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="temp" items="${requestScope.items}" varStatus="status">
                    <tr>
                        <td><c:out value="${temp.id}"/></td>
                        <td><c:out value="${temp.product.id}"/></td>
                        <td><c:out value="${temp.size}"/></td>
                        <td><c:out value="${temp.dough}"/></td>
                        <td>
                            <c:url value="/item/list/remove" var="itemDelete"/>
                            <form action="${itemDelete}" method="post">
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
                <c:url var="pagePreviousUrl" value="/item/list?page=${pagePrevious}"/>
                <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
                <c:set value="${requestScope.page}" var="page1"/>
                <c:url var="page1url" value="/item/list?page=${page1}"/>
                <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
                <c:set value="${requestScope.page + 1}" var="page2"/>
                <c:url var="page2url" value="/item/list?page=${page2}"/>
                <li class="page-item"><a class="page-link" href="${page2url}">${page2}</a></li>
                <c:set value="${requestScope.page + 2}" var="page3"/>
                <c:url var="page3url" value="/item/list?page=${page3}"/>
                <li class="page-item"><a class="page-link" href="${page3url}">${page3}</a></li>
                <li class="page-item"><a class="page-link" href="${page2url}">Next</a></li>
            </ul>
            <c:if test="${not empty requestScope.message}">
                <c:import url="../element/footer.jsp"/>
            </c:if>
        </div>
        <script>
            $(document).ready(function () {
                $("#searchInput").on("keyup", function () {
                    const value = $(this).val().toLowerCase();
                    $("#itemTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </div>
</div>
</body>
</html>
