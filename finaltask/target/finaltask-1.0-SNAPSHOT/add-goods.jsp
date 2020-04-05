<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 13.03.2020
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Goods</title>
</head>
<body>
<h1>Add product</h1>
<form action="bs">
    <input type="hidden" name="command" value="addGoods"/>
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td>
                <label>
                    <input type="text" name="name"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>
                <label>
                    <input type="text" name="description"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td>
                <label>
                    <input type="text" name="price"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>Picture:</td>
            <td>
                <label>
                    <input type="text" name="picture"/>
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
</body>
</html>
