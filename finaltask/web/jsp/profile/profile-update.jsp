<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h3>${sessionScope.user.login}'s profile</h3>
<form action="/pza" method="get">
    <table>
        <tbody>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="action" value="profileUpdate">
        <tr>
            <td>Login</td>
            <td>
                <input type="text" name="login" value="${user.login}" readonly>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="text" name="password" value="${user.password}">
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td>
                <input type="text" name="name" value="${profile.name}" required>
            </td>
        </tr>
        <tr>
            <td>Surname</td>
            <td>
                <input type="text" name="surname" value="${profile.surname}" required>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <input type="text" name="email" value="${profile.email}">
            </td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>
                <input type="text" name="phone" value="${profile.phone}" required>
            </td>
        </tr>
        <tr>
            <td>Address</td>
            <td>
                <input type="text" name="address" value="${profile.address}" required>
            </td>
        </tr>
        </tbody>
    </table>
    <br/>
    <input type="submit" value="Update">
</form>
</body>
</html>
