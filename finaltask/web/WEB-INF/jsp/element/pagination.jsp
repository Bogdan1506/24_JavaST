<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<ul class="pagination justify-content-center">
    <c:choose>
        <c:when test="${requestScope.page != 1}">
            <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
            <c:url var="pagePreviousUrl" value="${url}?page=${pagePrevious}"/>
            <li class="page-item"><a class="page-link" href="${pagePreviousUrl}"><fmt:message key="previous"
                                                                                              bundle="${rb}"/></a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item disabled"><a class="page-link"><fmt:message key="previous" bundle="${rb}"/></a></li>
        </c:otherwise>
    </c:choose>
    <c:set value="${requestScope.page}" var="page1"/>
    <c:url var="page1url" value="${url}?page=${page1}"/>
    <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
    <c:set value="${requestScope.page + 1}" var="pageNext"/>
    <c:url var="pageNextUrl" value="${url}?page=${pageNext}"/>
    <c:choose>
        <c:when test="${requestScope.page < requestScope.maxPage}">
            <li class="page-item"><a class="page-link" href="${pageNextUrl}"><fmt:message key="next"
                                                                                          bundle="${rb}"/></a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item disabled"><a class="page-link"><fmt:message key="next" bundle="${rb}"/></a></li>
        </c:otherwise>
    </c:choose>
</ul>