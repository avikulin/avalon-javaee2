<%--
  Created by IntelliJ IDEA.
  User: a.vikulin
  Date: 07.05.2022
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Devices list</title>
</head>
<body>
    <h1>Список устройств</h1>
    <div>
        <form action="devices.jsp" method="GET">
            <p>Фильтр:</p>
            <input type="text" name="vendor"> <br/>
            <input type="text" name="model"> <br/>
            <input type="date" name="date"> <br/>
            <input type="submit" value="Применить"> <br/>
    </div>
    <table>
        <th>
            <tr>
                <td>Номер</td>
                <td>Вендор</td>
                <td>Модель</td>
                <td>Дата производства</td>
            </tr>
        </th>
        <tbody>
            <tr>
                <td>

                </td>
            </tr>
            <tr>
                <td>

                </td>
            </tr>
            <tr>
                <td>

                </td>
            </tr>
            <tr>
                <td>

                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
