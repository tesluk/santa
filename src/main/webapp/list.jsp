<%@ page import="tab.os.tools.SantaCipher" %>
<%@ page import="java.security.KeyPair" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="tab.os.tools.DBSession" %>
<%@ page import="java.util.List" %>
<%@ page import="tab.os.entities.HappyUser" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<style>
    p {
        word-break: break-all;
    }

    tr:hover {
        background: #cccccc;
    }
</style>

<body>

<a href="santa.jsp">-Back-</a>

<img src="images/papers.jpg">

<% Session dbses = DBSession.getSession();
    List<HappyUser> users = dbses.createCriteria(HappyUser.class).list();%>

<h3>All users</h3>

<table width="80%">
    <tr>
        <th width="20%">Name</th>
        <th width="60%">Pulic key</th>
    </tr>
    <% for (HappyUser user : users) {%>
    <tr>
        <td><%=user.getName()%>
        </td>
        <td>
            <p><%= user.getPublicKey() == null ? "/empty/" : user.getPublicKey()%>
            </p>
        </td>
    </tr>

    <%} %>
</table>

</body>
</html>
