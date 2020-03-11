<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Output</title>
</head>
<body>
<c:forEach var="elem" items="${users}" varStatus="status">
    <ul>
        <li>
            <c:out value="${ elem }" default="LOL"/>
        </li>
    </ul>
    <br/>
</c:forEach>
<a href="index.jsp">Home</a>
</body>
</html>
