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
                    <input type="password" name="password" value="${param.password}">
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
