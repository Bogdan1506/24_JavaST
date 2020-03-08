<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Output</title>
</head>
<body>
<h1 align="center">${type}</h1>
<c:forEach var="elem" items="${orders}" varStatus="status">
    <ul>
        <li>
            <c:out value="${ elem }"/>
        </li>
    </ul>
    <br/>
</c:forEach>
<a href="index.jsp">Home</a>
</body>
</html>
