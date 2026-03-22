<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@ include file ="css/AAAstyle.css"%>
    </style>
    <title>Введите Ваш ID</title>
</head>
<body>
    <form action="Http://localhost:8080/">
        <label>Ваш ID: </br>
            <input class="inputID" type="text" name="id" maxlength="10">
        </label>
    </form>
    <h3>${ERROR}</h3>
</body>
</html>