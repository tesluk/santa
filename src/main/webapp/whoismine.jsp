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
<%
    Session dbses = DBSession.getSession();
    List<ShuffleResult> results = dbses.createCriteria(ShuffleResult.class).list();
%>

<a href="santa.jsp">-Back-</a>

<table border="0">
    <tr>
    <th>Encrypted value</th>
    </tr>
    <% for (ShuffleResult res : results) {%>
    <tr>
        <td><%=res.getValue()%>
        </td>
    </tr>

    <%} %>
</table>

<form method="post" action="/santa/whoismine">
    <textarea name="key" style="width: 90%; word-break: break-all" placeholder="your private key..."></textarea>
    <br>
    <input type="submit">
</form>
</body>
</html>
