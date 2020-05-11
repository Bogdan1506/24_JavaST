<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pagination</title>
</head>
<body>
<ul class="pagination justify-content-center">
    <c:choose>
        <c:when test="${requestScope.page != 1}">
            <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
            <c:url var="pagePreviousUrl" value="${url}?page=${pagePrevious}"/>
            <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item disabled"><a class="page-link">Previous</a></li>
        </c:otherwise>
    </c:choose>
    <c:set value="${requestScope.page}" var="page1"/>
    <c:url var="page1url" value="${url}?page=${page1}"/>
    <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
    <c:set value="${requestScope.page + 1}" var="pageNext"/>
    <c:url var="pageNextUrl" value="${url}?page=${pageNext}"/>
    <c:choose>
        <c:when test="${requestScope.page < requestScope.maxPage}">
            <li class="page-item"><a class="page-link" href="${pageNextUrl}">Next</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item disabled"><a class="page-link">Next</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</body>
</html>
