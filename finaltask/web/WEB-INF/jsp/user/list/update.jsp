<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Change role</title>
</head>
<body>
<c:import url="../../element/navbar.jsp"/>
<c:import url="../../element/admin-bar.jsp"/>
<div class="container">
    <p class="display-4 m-5" style="text-align: center">Update User Form</p>
    <c:url value="/user/list/role" var="changeRole"/>
    <form action="${changeRole}" class="form-control" name="userUpdate" method="post">
        <label for="sel">Select role:</label>
        <select class="form-control" id="sel" name="role">
            <option>Admin</option>
            <option>Creator</option>
            <option>Client</option>
        </select>
        <input type="hidden" name="id" value="${param.id}">
        <button type="submit" class="btn btn-info mt-5 float-lg-right">Update</button>
    </form>
</div>
</body>
</html>
