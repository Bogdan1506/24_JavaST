<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<div class="container mt-3">
    <jsp:include page="../bar.jsp"/>
    <h1>User list</h1>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table table-bordered">
        <thead>
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
                <form action="jsp/user/update-user.jsp" method="post">
                    <input type="hidden" name="id" value="${temp.id}"/>
                    <input type="hidden" name="login" value="${temp.login}"/>
                    <input type="hidden" name="password" value="${temp.password}"/>
                    <input type="hidden" name="role" value="${temp.role.id}"/>
                    <input type="submit" value="Update">
                </form>
            </td>
            <c:if test="${temp.role.id != 0}">
                <td>
                    <form action="pza" method="post">
                        <input type="hidden" name="action" value="userDelete">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
    </table>
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
</div>
</body>
</html>
