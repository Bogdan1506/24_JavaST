<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Menu</title>
</head>
<body>
<link rel="icon" href="data:,">

<jsp:include page="../bar.jsp"/>
<div class="container mt-3">
    <table class="table table-borderless" style="height: 100%;">
        <tbody>
        <c:forEach var="temp" items="${products}" begin="0" step="1" varStatus="count">
        <c:if test="${count.index == 0 || count.index % 3 == 0}">
        <tr>
            </c:if>
            <td style="height: 100%; width: 33%; vertical-align: text-bottom">
                <p>
                    <img alt="" src="${temp.picture}" width="250" height="250">
                </p>
                <h3>
                    <c:out value="${temp.name}"/>
                </h3>
                <p>
                    <button type="button" class="btn btn-light" data-toggle="tooltip" data-placement="right"
                            title="${temp.description}">
                        ingredients
                    </button>
                </p>
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-success active">
                        <input type="radio" name="options" id="dough1" autocomplete="off" checked> Thick
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="dough2" autocomplete="off"> Thin
                    </label>
                </div>
                <div style="vertical-align: bottom">
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-primary active">
                            <input type="radio" name="options" id="size1" autocomplete="off" checked> S ${temp.price}
                        </label>
                        <label class="btn btn-primary">
                            <c:set var="amountM" value="${temp.price * 2}"/>
                            <input type="radio" name="sz" id="size2" autocomplete="off"> M
                            <fmt:formatNumber type="number" pattern="##.#" value="${amountM}"/>
                        </label>
                        <c:set var="amountL" value="${temp.price * 3}"/>
                        <label class="btn btn-primary">
                            <input type="radio" name="options" id="size3" autocomplete="off"> L
                            <fmt:formatNumber type="number" pattern="##.#" value="${amountL}"/>
                        </label>
                    </div>
                    <form action="#" style="vertical-align: bottom">
                        <input type="hidden" name="id" value="${temp.id}">
                        <button type="button" class="btn btn-light">Add to cart</button>
                    </form>
                </div>
            </td>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
<%--                <script>
                    $(document).ready(function () {
                        $("#size2").click(function () {
                            $("#price").text("Hello world!");
                        });
                    });
                </script>--%>
