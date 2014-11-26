<%@ page import="java.util.Date" %>
<%@ page import="java.util.UUID" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>
<body>
<h1>Hello world!!!</h1>

<img src="images/dog.jpg" height="400">

<h3>Date=<%= new Date() %>
</h3>

<h3>Token=<%= UUID.randomUUID().toString().substring(0, 8)%>
</h3>

</body>
</html>
