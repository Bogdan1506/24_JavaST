<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Menu</title>
</head>
<body>
<jsp:include page="../element/main-bar.jsp"/>
<jsp:include page="menu-bar.jsp"/>
<div class="row">
    <div class="col-sm-10">
        <div class="container mt-3">
            <table id="tab" class="table table-borderless" style="height: 100%;">
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
                        <c:url value="/item/list" var="addToCart"/>
                        <form action="${addToCart}" method="post">
                            <c:if test="${temp.type eq 'PIZZA'}">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-success active" for="dough1">
                                        <input type="radio" name="dough" id="dough1" value="Thick" autocomplete="off"
                                               checked> Thick
                                    </label>
                                    <label class="btn btn-success" for="dough2">
                                        <input type="radio" name="dough" id="dough2" value="Thin" autocomplete="off">
                                        Thin
                                    </label>
                                </div>
                            </c:if>
                            <div style="vertical-align: bottom">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-primary active">
                                        <input type="radio" name="size" id="size1" value="Small" autocomplete="off"
                                               checked>
                                        S ${temp.price}
                                    </label>
                                    <label class="btn btn-primary">
                                        <c:set var="amountM" value="${temp.price * 2}"/>
                                        <input type="radio" name="size" id="size2" value="Medium" autocomplete="off"> M
                                        <fmt:formatNumber type="number" pattern="##.#" value="${amountM}"/>
                                    </label>
                                    <c:set var="amountL" value="${temp.price * 3}"/>
                                    <label class="btn btn-primary">
                                        <input type="radio" name="size" id="size3" value="Large" autocomplete="off"> L
                                        <fmt:formatNumber type="number" pattern="##.#" value="${amountL}"/>
                                    </label>
                                </div>
                                <br/>
                                <input type="hidden" name="id" value="${temp.id}">
                                <input type="submit" class="btn btn-light" value="Add to cart">
                            </div>
                        </form>
                    </td>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-sm-2">
        <jsp:include page="../item/list.jsp"/>
    </div>
</div>
<jsp:include page="../element/footer.jsp"/>
</body>
</html>

