<%--
  Created by IntelliJ IDEA.
  User: andrey.tesluk
  Date: 02.12.2015
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/js/flipclock.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/js/flipclock.js"></script>
</head>
<body>
<div class="content" style="text-align: center; padding: 30px">
    <img src="http://www.stb.ua/wp-content/uploads/2015/10/640_4001.jpg">
</div>
<div class="content" style="text-align: center">
    <div class="clock" style="display: inline-block; width: 470px"></div>
</div>

<script type="text/javascript">
    var clock;

    $(document).ready(function () {
        var clock;

        var date = new Date(Date.UTC(2016, 1, 22, 18, 0, 0));
        var now = new Date();
        var diff = date.getTime() / 1000 - now.getTime() / 1000 - 2 * 60 * 60;

        if (diff > 0) {
            clock = $('.clock').FlipClock(diff, {
                countdown: true
            });
        } else {
            clock = $('.clock').FlipClock(0, {
                countdown: false
            })
        }

    });
</script>

</body>
</html>
