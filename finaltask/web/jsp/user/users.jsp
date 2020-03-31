<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<div align="center">
    <jsp:include page="../bar.jsp"/>
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
                    <td><a href="${DeleteUserLink}">Delete</a></td>
                </c:if>
                <c:url var="DeleteUserLink" value="pza">
                    <c:param name="action" value="userDelete"/>
                    <c:param name="id" value="${temp.id}"/>
                </c:url>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    ${message}
</div>
</body>
</html>
