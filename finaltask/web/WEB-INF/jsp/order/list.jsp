<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="orderList" bundle="${rb}"/></title>
</head>
<body>
<c:import url="../element/navbar.jsp"/>
<c:import url="../element/admin-bar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <p style="text-align: center" class="display-4"><fmt:message key="orderList" bundle="${rb}"/></p>
            <input class="form-control" id="searchInput" type="text"
                   placeholder="<fmt:message key="search" bundle="${rb}"/>" aria-label="Search">
            <br/>
            <table class="table table-bordered" id="orderTable">
                <thead class="thead-light">
                <tr>
                    <th scope="row"><fmt:message key="id" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="profile" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="date" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="delete" bundle="${rb}"/></th>
                    <th scope="row"><fmt:message key="update" bundle="${rb}"/></th>
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
                                <input type="submit" value="<fmt:message key="delete" bundle="${rb}"/>">
                            </form>
                        </td>
                        <td>
                            <c:url value="/order/list/update-form" var="orderUpdate"/>
                            <form action="${orderUpdate}" method="post">
                                <input type="hidden" name="id" value="${temp.profile.id}"/>
                                <input type="submit" value="<fmt:message key="update" bundle="${rb}"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:url var="url" value="/order/list"/>
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
            $("#orderTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
