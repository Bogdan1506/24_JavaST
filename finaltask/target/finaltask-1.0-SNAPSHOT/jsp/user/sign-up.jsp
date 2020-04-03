<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign up</title>
</head>
<body>
<div align="center">
    <h1>Sign up</h1>
    <form action="/pza" name="signUp">
        <input type="hidden" name="action" value="userCreate">
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
                        <input type="password" name="password" id="pass" required/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td>
                    <label>
                        <input type="password" name="repPassword" id="repPass" required/>
                    </label>
                </td>
            <tr>
                <td></td>
                <td>${message}</td>
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
<script>
    var password = document.getElementById("pass")
        , confirm_password = document.getElementById("repPass");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
