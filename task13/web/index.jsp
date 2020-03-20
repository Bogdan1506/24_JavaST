<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>uploadImage</title>
</head>
<body>
<h1>Fill form</h1>
<form action="parser" method="post" enctype="multipart/form-data">
    <input name="data" type="file"><br>
    <label>
        <select name="typeParser">
            <option>SAX</option>
            <option>StAX</option>
            <option>DOM</option>
        </select>
    </label> <br>
    <input type="submit"><br>
</form>
</body>
</html>
