<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User List</title>


    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Data Tables</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>

    <script
            src="http://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

</head>
<body>
<link rel="icon" href="data:,">


<jsp:include page="../main-bar.jsp"/>
<div class="container mt-3">
    <h1 style="align-content: center">User list</h1>
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
<jsp:include page="../footer.jsp"/>
<%--<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>--%>

<script>
    $(document).ready(function () {
        $('#userTable').DataTable();
    });
</script>
</body>
</html>
