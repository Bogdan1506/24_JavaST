<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
</head>
<body>
<div align="center">
    <h1>Sign in</h1>
    <form action="pza" name="signIn">
        <table>
            <tbody>
            <tr>
                <td>Login:</td>
                <td>
                    <label>
                        <input type="text" name="login"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <label>
                        <input type="text" name="password"/>
                    </label>
                </td>
            </tr>
            <input type="hidden" name="action" value="userSignIn">
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Submit">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
