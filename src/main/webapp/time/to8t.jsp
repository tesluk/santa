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
    <link rel="stylesheet" href="/css/8m.css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/js/flipclock.js"></script>
    <script>var x = 46336879;</script>
</head>
<body>
<div id="cf" class="content" style="text-align: center; padding: 30px">
    <a href="http://www.panoramio.com/photo/46336879">
        <img class="bottom" width="600px" height="600px" src="/images/8ma.png">
        <img class="top" width="600px" height="600px" src="/images/8ma-top.png">
    </a>
</div>
<div class="content" style="text-align: center">
    <p>Be here ^ in:</p>

    <div class="clock" style="display: inline-block; width: 530px"></div>
</div>

<script type="text/javascript">
    var clock;

    $(document).ready(function () {
        var clock;

        var date = new Date(Date.UTC(2016, 2, 26, 12, 22, 50));
        var now = new Date();
        var diff = date.getTime() / 1000 - now.getTime() / 1000 - 2 * 60 * 60;

        if (diff > 0) {
            clock = $('.clock').FlipClock(diff, {
                countdown: true
            });
        } else {
            clock = $('.clock').FlipClock(0, {
                autoStart: false
            })
        }

    });
</script>

</body>
</html>
