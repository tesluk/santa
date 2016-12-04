<%@ page import="org.hibernate.Session" %>
<%@ page import="tab.os.db.DBSession" %>
<%@ page import="java.util.List" %>
<%@ page import="tab.os.santa.entities.ShuffleResult" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<body>
<a href="santa.jsp">-Back-</a>

<form method="post" action="/santa/whoami">
    <input type="text" name="invite" placeholder="Your invite..." >
    <br>
    <input type="submit">
</form>
</body>
</html>
