<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Update user</h1>
<form action="${pageContext.request.contextPath}pza">
    <input type="hidden" name="action" value="userUpdate">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <table>
        <tbody>
        <tr>
            <td>
                login
            </td>
            <td>
                <label>
                    <input type="text" value="<%=request.getParameter("login")%>" readonly>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                password
            </td>
            <td>
                <label>
                    <input type="text" name="password" value="<%=request.getParameter("password")%>">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                role
            </td>
            <td>
                <label>
                    <select name="role">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update">
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
