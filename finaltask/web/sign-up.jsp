<jsp:useBean id="UserCommand" scope="request" type="by.avdeev.pizzeria.controller.command.impl.UserCommand"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign up</title>
</head>
<body>
<div align="center">
    <h1>Sign up</h1>
    <form action="bs" name="signUp">
        <input type="hidden" name="command" value="${UserCommand}">
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
