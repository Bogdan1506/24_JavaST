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
