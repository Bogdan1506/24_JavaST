<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.local.value}"/>
<fmt:setBundle basename="content" var="rb" scope="session"/>
<form action="${requestScope['javax.servlet.forward.request_uri']}" class="form-control" method="post">
    <label for="sel">Page size</label><select class="form-control" style="width: 90px" id="sel" name="pageSize">
    <option>20</option>
    <option>50</option>
    <option>100</option>
</select>
    <button type="submit" class="btn btn-primary"><fmt:message key="change" bundle="${rb}"/></button>
</form>
