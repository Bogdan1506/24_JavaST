<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>
</head>
<body>
<link rel="icon" href="data:,">


<jsp:include page="../bar.jsp"/>
<div class="container mt-3">
    <h1>User list</h1>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">login</th>
            <th scope="row">password</th>
            <th scope="row">role</th>
            <th scope="row" colspan="2">method</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <tr>
            <c:forEach var="temp" items="${users}" varStatus="status">
        <tr>
            <td><c:out value="${temp.id}"/></td>
            <td><c:out value="${temp.login}"/></td>
            <td><c:out value="${temp.password}"/></td>
            <td><c:out value="${temp.role}"/></td>
            <td>
                <c:url value="/user/list/update" var="updateRole"/>
                <form action="${updateRole}" method="post">
                    <input type="hidden" name="id" value="${temp.id}"/>
                    <input type="submit" value="Update">
                </form>
            </td>
            <c:if test="${temp.role.id != 0}">
                <td>
                    <c:url value="/user/delete" var="userDelete"/>
                    <form action="${userDelete}" method="post">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp"/>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
