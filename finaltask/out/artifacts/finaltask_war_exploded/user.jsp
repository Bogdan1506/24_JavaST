<%@ page import="by.avdeev.pizzeria.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<div align="center">
    <h1>User list</h1>
    <table border="2">
        <thead>
        <th scope="row">id</th>
        <th scope="row">login</th>
        <th scope="row">password</th>
        <th scope="row">role</th>
        <th scope="row">method</th>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${users}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.login}"/></td>
                <td><c:out value="${temp.password}"/></td>
                <td><c:out value="${temp.role}"/></td>
                <c:url var="UpdateUserLink" value="update-user.jsp">
                    <c:param name="id" value="${temp.id}"/>
                    <c:param name="login" value="${temp.login}"/>
                    <c:param name="password" value="${temp.password}"/>
                    <c:param name="role" value="${temp.role.name}"/>
                </c:url>
                <c:url var="DeleteUserLink" value="pza">
                    <c:param name="action" value="userDelete"/>
                    <c:param name="id" value="${temp.id}"/>
                </c:url>
                <td><a href="${UpdateUserLink}">Update</a></td>
                <td><a href="${DeleteUserLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
