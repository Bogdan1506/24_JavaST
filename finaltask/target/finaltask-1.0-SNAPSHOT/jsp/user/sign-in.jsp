<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
</head>
<body>
<div align="center">
    <h1>Sign in</h1>
    <form action="/pza" name="signIn" method="post">
        <input type="hidden" name="action" value="userSignIn">
        <table>
            <tbody>
            <tr>
                <td>Login:</td>
                <td>
                    <label>
                        <input type="text" name="login" required/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <label>
                        <input type="password" name="password" required/>
                    </label>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    ${message}
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Submit">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <c:choose>
        <c:when test="${not empty message}">
            <a href="jsp/user/sign-up.jsp">Sign up</a>
        </c:when>
        <c:otherwise>
            <a href="sign-up.jsp">Sign up</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
