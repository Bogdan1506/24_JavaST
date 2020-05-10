<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select</title>
</head>
<body>
<form action="${requestScope['javax.servlet.forward.request_uri']}" class="form-control" method="post">
    <label for="sel">Page size</label><select class="form-control" style="width: 90px" id="sel" name="pageNum">
        <option>20</option>
        <option>50</option>
        <option>100</option>
    </select>
    <button type="submit" class="btn btn-primary">Choose</button>
</form>
</body>
</html>
