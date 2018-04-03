<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <!DOCTYPE html>
    <html lang="en">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <head>
        <title>Redirect</title>
        <link rel="stylesheet" type="text/css" href="resources/css/home.css" />

    </head>

    <body background="resources/css/img/red2.jpg">

        <header>
            <h1 class="head">${message}</h1>

            <script>
                setInterval(function() {
                    window.location.href = "profile?";
                }, 800);

            </script>
        </header>
    </body>

    </html>
