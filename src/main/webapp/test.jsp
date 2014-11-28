<%@ page import="tab.os.tools.SantaCipher" %>
<%@ page import="java.security.KeyPair" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.UUID" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<style>
    h3:hover {
        color: #EA0011;
    }
</style>

<body>
<h1>Hello world!!!</h1>

<% KeyPair key = SantaCipher.generateKeyPair();
    String privateKey = Base64.getEncoder().encodeToString(key.getPrivate().getEncoded());
    String publicKey = Base64.getEncoder().encodeToString(key.getPublic().getEncoded());%>

<p>Public=<%= publicKey %>
</p>

<p>Private=<%= privateKey%>
</p>

</body>
</html>
