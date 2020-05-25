<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="orderPosList" bundle="${rb}"/></title>
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
            <p style="text-align: center" class="display-4"><fmt:message key="orderPosList" bundle="${rb}"/></p>
            <input class="form-control" id="searchInput" type="text"
                   placeholder="<fmt:message key="search" bundle="${rb}"/>" aria-label="Search">
            <br/>
            <table class="table table-bordered" id="orderPosTable">
                <thead class="thead-light">
                <tr>
                    <th scope="row"><fmt:message key="id" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="item" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="order" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="price" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="delete" bundle="${rb}"/></th>
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
                                <input type="submit" value="<fmt:message key="delete" bundle="${rb}"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:url var="url" value="/orderposition/list"/>
            <c:import url="../element/pagination.jsp"/>
            <c:if test="${not empty requestScope.message}">
                <c:import url="../element/footer.jsp"/>
            </c:if>
        </div>
        <div class="col-2"></div>
        <div class="col-1">
            <c:import url="../element/select-num-page.jsp"/>
        </div>
    </div>
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
</body>
</html>
