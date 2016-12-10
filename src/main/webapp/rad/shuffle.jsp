<%@ page import="org.hibernate.Session" %>
<%@ page import="tab.os.db.DBSession" %>
<%@ page import="tab.os.yana.santa.entity.ShufflePair" %>
<%@ page import="java.util.List" %>
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

<%
    Session s = DBSession.getSession();
    List<ShufflePair> list = s.createCriteria(ShufflePair.class).list();
%>
<table>
    <% for (ShufflePair p : list) { %>
<tr>
    <td><%=p.getWho()%></td>
    <td><%=p.getTo()%></td>
</tr>
    <% } %>
</table>


</body>
</html>
