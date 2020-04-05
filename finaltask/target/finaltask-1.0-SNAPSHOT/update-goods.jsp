<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update product</title>
</head>
<body>
<h1>Update product</h1>
<form action="${pageContext.request.contextPath}bs">
    <input type="hidden" name="command" value="updateGoods">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <table>
        <tbody>
        <tr>
            <td>
                name
            </td>
            <td>
                <label>
                    <input type="text" name="name" value="<%=request.getParameter("name")%>">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                description
            </td>
            <td>
                <label>
                    <input type="text" name="description" value="<%=request.getParameter("description")%>">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                price
            </td>
            <td>
                <label>
                    <input type="text" name="price" value="<%=request.getParameter("price")%>">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                picture
            </td>
            <td>
                <label>
                    <input type="text" name="picture" value="<%=request.getParameter("picture")%>">
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
