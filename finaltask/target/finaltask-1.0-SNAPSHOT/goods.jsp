<%@ page import="by.avdeev.pizzeria.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Goods List</title>
</head>
<body>
<div align="center">
    <h1>Goods list</h1>
    <a href="add-product.jsp">create</a>
    <table border="2">
        <thead>
        <th scope="row">id</th>
        <th scope="row">name</th>
        <th scope="row">description</th>
        <th scope="row">price</th>
        <th scope="row">picture</th>
        <th scope="row">method</th>
        </thead>
        <tbody>
        <c:forEach var="temp" items="${product}" varStatus="status">
            <tr>
                <td><c:out value="${temp.id}"/></td>
                <td><c:out value="${temp.name}"/></td>
                <td><c:out value="${temp.description}"/></td>
                <td><c:out value="${temp.price}"/></td>
                <td>
                    <img alt="" src="${temp.picture}"/>
                </td>
                <form action="bs">
                    <td>
                        <select name="dough">
                            <option>Thin</option>
                            <option>Thick</option>
                        </select>
                    </td>
                    <td>
                        <select name="size">
                            <option>Small</option>
                            <option>Medium</option>
                            <option>Large</option>
                        </select>
                    </td>
                    <input type="hidden" name="command" value="createItem">
                    <input type="hidden" name="goodsId" value="${temp.id}">
                    <td><input type="submit" value="Submit"></td>
                </form>
                <c:url var="UpdateGoodsLink" value="update-product.jsp">
                    <c:param name="id" value="${temp.id}"/>
                    <c:param name="name" value="${temp.name}"/>
                    <c:param name="description" value="${temp.description}"/>
                    <c:param name="price" value="${temp.price}"/>
                    <c:param name="picture" value="${temp.picture}"/>
                </c:url>
                <c:url var="DeleteGoodsLink" value="bs">
                    <c:param name="command" value="deleteGoods"/>
                    <c:param name="id" value="${temp.id}"/>
                </c:url>
                <td><a href="${UpdateGoodsLink}">Update</a></td>
                <td><a href="${DeleteGoodsLink}">Delete</a></td>
                <td><a href="${AddGoodsToOrderLink}">Add to cart</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="bs">
        <input type="hidden" name="command" value="getSessionItems">
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
