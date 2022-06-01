<%--
  Created by IntelliJ IDEA.
  User: a.vikulin
  Date: 06.05.2022
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Servlet MainPage</title>
  </head>
  <body>
    <div>
      <h1>Authorization</h1>
      <form action="auth" method="POST">
        <input type="text" name="login" /><br><br>
        <input type="password" name="password" /><br><br>
        <input type="submit" name="Singin" value="Singin" /><br><br>
      </form>
    </div>
  </body>
</html>
