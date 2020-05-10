<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delivery List</title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="../element/admin-bar.jsp"/>
<div class="container-fluid">
    <div class="row">

        <div class="col-3">
            <ctg:deliveryCount countTotal="${requestScope.countTotal}" countToday="${requestScope.countToday}"/>
        </div>
        <div class="col-6">
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
                <c:choose>
                    <c:when test="${requestScope.page != 1}">
                        <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
                        <c:url var="pagePreviousUrl" value="/delivery/list?page=${pagePrevious}"/>
                        <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled"><a class="page-link">Previous</a></li>
                    </c:otherwise>
                </c:choose>
                <c:set value="${requestScope.page}" var="page1"/>
                <c:url var="page1url" value="/delivery/list?page=${page1}"/>
                <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
                <c:set value="${requestScope.page + 1}" var="pageNext"/>
                <c:url var="pageNextUrl" value="/delivery/list?page=${pageNext}"/>
                <c:choose>
                    <c:when test="${requestScope.page * 20 < requestScope.countTotal}">
                        <li class="page-item"><a class="page-link" href="${pageNextUrl}">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled"><a class="page-link">Next</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <c:if test="${not empty requestScope.message}">
                <c:import url="../element/footer.jsp"/>
            </c:if>
        </div>
        <div class="col-2"></div>
        <div class="col-1">
            <c:import url="../element/select-num-page.jsp"/>
        </div>
        <script>
            $(document).ready(function () {
                $("#searchInput").on("keyup", function () {
                    const value = $(this).val().toLowerCase();
                    $("#deliveryTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </div>
</div>
</body>
</html>
