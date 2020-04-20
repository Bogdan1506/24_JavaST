<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>


</head>
<body>
<jsp:include page="../element/navbar.jsp"/>
<jsp:include page="../element/admin-bar.jsp"/>
<div class="container mt-3">
    <table class="table table-bordered" id="userTable">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">login</th>
            <th scope="row">password</th>
            <th scope="row">role</th>
            <th scope="row">update</th>
            <th scope="row">delete</th>
        </tr>
        </thead>
        <tbody>
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
                <td>
                    <c:if test="${temp.role.id != 0}">
                        <c:url value="/user/delete" var="userDelete"/>
                        <form action="${userDelete}" method="post">
                            <input type="hidden" name="id" value="${temp.id}"/>
                            <input type="submit" value="Delete">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../element/footer.jsp"/>
<script>
    $(document).ready(function () {
        $('#userTable').DataTable();
    });
</script>
</body>
</html>
