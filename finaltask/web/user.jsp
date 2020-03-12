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
                <c:url var="UpdateLink" value="update-user.jsp">
                    <c:param name="id" value="${temp.id}"/>
                    <c:param name="login" value="${temp.login}"/>
                    <c:param name="password" value="${temp.password}"/>
                    <c:param name="role" value="${temp.role}"/>
                </c:url>
                <c:url var="DeleteLink" value="bs">
                    <c:param name="command" value="delete"/>
                    <c:param name="id" value="${temp.id}"/>
                </c:url>
                <td><a href="${UpdateLink}">Update</a></td>
                <td><a href="${DeleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
