<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>
</head>
<body>
<jsp:include page="../bar.jsp"/>
<div class="container mt-3">
    <h1>Menu list</h1>
    <table class="table table-bordered">
        <tbody>
        <c:forEach var="temp" items="${products}" begin="0" step="1" varStatus="count">
        <c:if test="${count.index == 0 || count.index % 3 == 0}">
        <tr>
            </c:if>
            <td width="30">
                <p>
                    <img alt="" src="${temp.picture}" width="250" height="250">
                </p>
                <p><strong><c:out value="${temp.name}"/></strong></p>
                <p><c:out value="${temp.description}"/></p>
                <p><c:out value="${temp.price}"/></p>
                <input id="btn1" type="submit" value="click">
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-success active">
                        <input type="radio" name="options" id="dough1" autocomplete="off" checked> Thick
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="dough2" autocomplete="off"> Thin
                    </label>
                </div>
                </br>
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-primary active">
                        <input type="radio" name="options" id="size1" autocomplete="off" checked> Small
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="size2" autocomplete="off"> Medium
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="size3" autocomplete="off"> Large
                    </label>
                </div>
            </td>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp"/>
<script>
    $(document).ready(function () {
        $("#btn1").click(function () {
            $("#price").text("Hello world!");
        });
    });
</script>
</body>
</html>
