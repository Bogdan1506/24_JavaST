<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order Position List</title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="../element/admin-bar.jsp"/>
<div class="container-fluid">
    <div class="row">

        <div class="col-3">
            <ctg:productCountTag rows="${requestScope.count.size}" head="orders">
                ${requestScope.count.count}
            </ctg:productCountTag>
        </div>
        <div class="col-6">
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
                        <td>
                            <c:set var="price" value="${temp.price}"/>
                            <fmt:formatNumber type="number" pattern="##.#" value="${price}"/>
                        </td>
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
                <c:choose>
                    <c:when test="${requestScope.page != 1}">
                        <c:set value="${requestScope.page - 1}" var="pagePrevious"/>
                        <c:url var="pagePreviousUrl" value="/orderposition/list?page=${pagePrevious}"/>
                        <li class="page-item"><a class="page-link" href="${pagePreviousUrl}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled"><a class="page-link">Previous</a></li>
                    </c:otherwise>
                </c:choose>
                <c:set value="${requestScope.page}" var="page1"/>
                <c:url var="page1url" value="/orderposition/list?page=${page1}"/>
                <li class="page-item"><a class="page-link" href="${page1url}">${page1}</a></li>
                <c:set value="${requestScope.page + 1}" var="pageNext"/>
                <c:url var="pageNextUrl" value="/orderposition/list?page=${pageNext}"/>
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
        <script>
            $(document).ready(function () {
                $("#searchInput").on("keyup", function () {
                    const value = $(this).val().toLowerCase();
                    $("#orderPosTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </div>
</div>
</body>
</html>
