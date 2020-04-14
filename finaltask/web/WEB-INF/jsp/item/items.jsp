<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>


</head>
<body>
<jsp:include page="../element/main-bar.jsp"/>
<div class="container mt-3">
    <h1 style="align-content: center">User list</h1>
    <table class="table table-bordered" id="userTable">
        <thead class="thead-light">
        <tr>
            <th scope="row">id</th>
            <th scope="row">name</th>
            <th scope="row">size</th>
            <th scope="row">dough</th>
            <th scope="row">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${items}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.product.id}"/></td>
                <td><c:out value="${temp.size}"/></td>
                <td><c:out value="${temp.dough}"/></td>
                <td>
                    <c:url value="/item/delete" var="userDelete"/>
                    <form action="${itemDelete}" method="post">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input type="submit" value="Delete">
                    </form>
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
