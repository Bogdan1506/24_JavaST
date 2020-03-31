<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
</head>
<body>
<div align="center">
    <h1>Sign in</h1>
    <form action="/pza" name="signIn">
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
    <a href="sign-up.jsp">Sign up</a>
</div>
</body>
</html>
