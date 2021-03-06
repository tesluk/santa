<%@ page import="tab.os.santa.tools.SantaCipher" %>
<%@ page import="java.security.KeyPair" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<style>
    p {
        width: 400px;
        word-break: break-all;
    }

    p:hover {
        color: #EA0011;
    }
</style>

<body>

<a href="santa.jsp">-Back-</a>

<img src="images/papers.jpg">

<% KeyPair key = SantaCipher.generateKeyPair();
    String privateKey = Base64.encodeBase64String(key.getPrivate().getEncoded());
    String publicKey = Base64.encodeBase64String(key.getPublic().getEncoded());%>

<h3>Public</h3>

<p><%= publicKey %>
</p>

<h3>Private</h3>

<p><%= privateKey%>
</p>

<form method="post" action="/santa/regme">
    <td><input type="hidden" name="key" value="<%=publicKey%>>"></td>
    <table border="0">
        <tr>
            <td>Invite code:</td>
            <td><input type="text" name="invite" required></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="I like it! Use this key!"></td>
        </tr>

    </table>
</form>

</body>
</html>
