<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to OpenShift</title>
</head>

<body>

<a href="santa.jsp">-Back-</a>

<form method="post" action="/santa/regme">

    <table border="0">
        <tr>
            <td>Invite code:</td>
            <td><input type="text" name="invite" required></td>
        </tr>
        <tr>
            <td>Your PUBLIC key:</td>
            <td><textarea name="key" style="width: 90%" required></textarea></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>

    </table>
</form>
</body>
</html>
