<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<body>

<a href="santa.jsp">-Back-</a>

<form method="post" action="/tableau/crypt">

    <table border="0">
        <tr>
            <td>Modulus:</td>
            <td><input type="text" name="modulus" required></td>
        </tr>
        <tr>
            <td>Exponent:</td>
            <td><input type="text" name="exponent" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password" required></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>

    </table>
</form>
</body>
</html>
