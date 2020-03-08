<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>$Title$</title>
</head>
<body>
<h2>Fill form</h2>
<form action="${pageContext.request.contextPath}/parser">
    <table>
        <tbody>
        <tr>
            <td>
                File: <label>
                <input type="text" name="filename">
            </label>
            </td>
        </tr>
        <tr>
            <td>
                Parser: <label>
                <select name="typeParser">
                    <option>SAX</option>
                    <option>StAX</option>
                    <option>DOM</option>
                </select>
            </label>
            </td>
        </tr>
        <tr align="right">
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>