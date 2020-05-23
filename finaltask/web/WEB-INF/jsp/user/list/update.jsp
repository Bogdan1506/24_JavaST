<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<c:import url="../../element/navbar.jsp"/>
<c:import url="../../element/admin-bar.jsp"/>
<div class="container">
    <p class="display-4 m-5" style="text-align: center"><fmt:message key="userUpdateForm" bundle="${rb}"/></p>
    <c:url value="/user/list/role" var="changeRole"/>
    <form action="${changeRole}" name="userUpdate" method="post">
        <ul>
            <li>

                <label for="client"> <fmt:message key="client" bundle="${rb}"/>
                    <input id="client" type="radio" autocomplete="on" name="role" value="2">
                </label>
            </li>
            <li>

                <label for="creator"> <fmt:message key="creator" bundle="${rb}"/>
                    <input id="creator" type="radio" autocomplete="on" name="role" value="1">
                </label>
            </li>
            <li>
                <label for="admin"> <fmt:message key="admin" bundle="${rb}"/>
                    <input id="admin" type="radio" autocomplete="on" name="role" value="0">
                </label>
            </li>
        </ul>


        <%--        <label for="sel"><fmt:message key="role" bundle="${rb}"/></label>
                <select class="form-control" id="sel" name="role">
                    <option><fmt:message key="admin" bundle="${rb}"/></option>
                    <option><fmt:message key="creator" bundle="${rb}"/></option>
                    <option><fmt:message key="client" bundle="${rb}"/></option>
                </select>--%>
        <input type="hidden" name="id" value="${param.id}">
        <button type="submit" class="btn btn-info mt-5 float-lg-right"><fmt:message key="update"
                                                                                    bundle="${rb}"/></button>
    </form>
</div>
