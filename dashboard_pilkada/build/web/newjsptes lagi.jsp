<%-- 
    Document   : newjsptes lagi
    Created on : 04-Dec-2020, 11:09:53
    Author     : matajari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jquery-3.4.1.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
    <script>
        jQuery(document).ready(function ($) {
            function toDataUrl(url, callback) {
                var xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    var reader = new FileReader();
                    reader.onloadend = function () {
                        callback(reader.result);
                    }
                    reader.readAsDataURL(xhr.response);
                };
                xhr.open('GET', url);
                xhr.responseType = 'blob';
                xhr.send();
            }
            var proxyUrl = 'https://cors-anywhere.herokuapp.com/',
                    targetUrl = 'http://103.41.204.105/pilkada/candidate/muhamad-saras.png'
            toDataUrl(proxyUrl + targetUrl,
                    function (data) {
                        console.log(data)
                    });
        });
    </script>
</html>
