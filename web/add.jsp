<%--
  Created by IntelliJ IDEA.
  User: a.vikulin
  Date: 07.05.2022
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add device</title>
</head>
<body>
    <h1>Добавить устройство</h1>
    <div>
        <form action="/add" method="post">
            <input type="text" name="vendor">
            <input type="text" name="model">
            <input type="date" name="date">
            <input type="submit" value="Добавить">
        </form>
    </div>
</body>
</html>
