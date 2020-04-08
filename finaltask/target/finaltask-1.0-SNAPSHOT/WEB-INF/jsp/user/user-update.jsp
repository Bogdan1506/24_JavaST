<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Update</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Update User Form</h2>
    <form action="/pza?action=userUpdate" class="was-validated" name="userUpdate" method="post">
        <label for="sel">Select role:</label>
        <select class="form-control" id="sel" name="role">
            <option>Admin</option>
            <option>Creator</option>
            <option>Client</option>
        </select>
        <input type="hidden" name="id" value="${param.id}">
        <button type="submit" class="btn btn-info">Update</button>
    </form>
    </div>
</body>
</html>
