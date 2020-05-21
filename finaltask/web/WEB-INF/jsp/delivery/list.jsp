<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="deliveryList" bundle="${rb}"/></title>
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
            <p style="text-align: center" class="display-4"><fmt:message key="deliveryList" bundle="${rb}"/></p>
            <input class="form-control" id="searchInput" type="text"
                   placeholder="<fmt:message key="search" bundle="${rb}"/>" aria-label="Search">
            <br/>
            <table class="table table-bordered" id="deliveryTable">
                <thead class="thead-light">
                <tr>
                    <th scope="row"><fmt:message key="id" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="orderPos" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="date" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="payment" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="delete" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="update" bundle="${rb}"/></th>
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
                                <input type="submit" value="<fmt:message key="delete" bundle="${rb}"/>">
                            </form>
                        </td>
                        <td>
                            <c:url value="/delivery/list/update-form" var="deliveryUpdate"/>
                            <form action="${deliveryUpdate}" method="post">
                                <input type="hidden" name="id" value="${temp.id}"/>
                                <input type="submit" value="<fmt:message key="update" bundle="${rb}"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:url var="url" value="/delivery/list"/>
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
            $("#deliveryTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
