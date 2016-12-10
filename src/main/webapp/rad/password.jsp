<%--
  Created by IntelliJ IDEA.
  User: andrey.tesluk
  Date: 06.12.2016
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secret Santa</title>
</head>
<body>

<form action="/radik/shuffle" method="post">
    <input type="text" name="password">
    <input type="checkbox" name="generate">
    <input type="submit">
</form>

</body>
</html>
