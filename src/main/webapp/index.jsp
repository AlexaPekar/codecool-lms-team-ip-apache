<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <script type="text/javascript" src="resources/js/login.js"></script>
    <link rel="shortcut icon" href="resources/icons/titleIcon.png"/>
    <title>Login</title>
</head>

<body>
    <header>
        <h1 id="message-field" >${message}</h1>
    </header>
    <div class="form-div">
        <form id="myForm" name="login" action="login" onsubmit="return validateLoginForm();"method="POST">
            <div class="input-div">
                <h2 id="h2">Logging in</h2><br>E-mail:<br>
                <input type="email" name="email" class="form-el" id="email"><br> Password:<br>
                <input type=password name="password" class="form-el" id="password"><br>
                <p id="registration-para" class="register-paragraph">If you don't have an account <a class="register-link" href="register.jsp" >register</a> one</p>
                <input id="submit" type="submit" value="Login" class="btn">
            </div>
        </form>
    </div>
</body>
</html>
