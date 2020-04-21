<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>


</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../element/admin-bar.jsp"/>
<div class="container mt-3">
    <p style="text-align: center" class="display-4">User list</p>
    <table class="table table-bordered" id="userTable">
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
        <c:forEach var="temp" items="${items}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.product.id}"/></td>
                <td><c:out value="${temp.size}"/></td>
                <td><c:out value="${temp.dough}"/></td>
                <td>
                    <c:url value="/item/items/remove" var="itemDelete"/>
                    <form action="${itemDelete}" method="post">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="container">
        <ul class="pagination">
            <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
            <c:url var="pagePreviousUrl" value="/item/items?page=${pagePrevious}"/>
            <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
            <c:set value="${requestScope.page}" var="page1"/>
            <c:url var="page1url" value="/item/items?page=${page1}"/>
            <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
            <c:set value="${requestScope.page + 1}" var="page2"/>
            <c:url var="page2url" value="/item/items?page=${page2}"/>
            <li class="page-item"><a class="page-link" href="${page2url}">${page2}</a></li>
            <c:set value="${requestScope.page + 2}" var="page3"/>
            <c:url var="page3url" value="/item/items?page=${page3}"/>
            <li class="page-item"><a class="page-link" href="${page3url}">${page3}</a></li>
<%--            <c:set value="${requestScope.page + 1}" var="pageNext"/>
            <c:url var="pageNextUrl" value="/item/items?page=${pageNext}"/>--%>
            <li class="page-item"><a class="page-link" href="${page2url}">Next</a></li>
        </ul>
    </div>
</div>
</body>
</html>
