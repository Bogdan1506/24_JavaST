<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Update User Form</h2>
    <form action="/pza?action=userUpdate" class="was-validated" name="userUpdate" method="post">
        <label for="sel">Select role:</label>
        <select class="form-control" id="sel" name="role">
            <option>Admin</option>
            <option>Creator</option>
            <option>Client</option>
        </select>
        <input type="hidden" name="id" value="${param.id}">
        <button type="submit" class="btn btn-info">Update</button>
    </form>
    </div>
</body>
</html>


<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Update user</h1>
<form action="<c:url value="/pza"/>" method="post">
    <input type="hidden" name="action" value="userUpdate">
    <input type="hidden" name="id" value="${param.id}">
    <table>
        <tbody>
        <tr>
            <td>
                login
            </td>
            <td>
                <label>
                    <input type="text" name="login" value="${param.login}" readonly>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                password
            </td>
            <td>
                <label>
                    <input type="password" name="password" value="${param.password}" readonly>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                role
            </td>
            <td>
                <label>
                    <c:choose>
                        <c:when test="${param.role eq 0}">
                            <select name="role">
                                <option>admin</option>
                                <option>creator</option>
                                <option>client</option>
                            </select>
                        </c:when>
                        <c:when test="${param.role eq 1}">
                            <select name="role">
                                <option>creator</option>
                                <option>admin</option>
                                <option>client</option>
                            </select>
                        </c:when>
                        <c:when test="${param.role eq 2}">
                            <select name="role">
                                <option>client</option>
                                <option>admin</option>
                                <option>creator</option>
                            </select>
                        </c:when>
                    </c:choose>
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update">
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
--%>
