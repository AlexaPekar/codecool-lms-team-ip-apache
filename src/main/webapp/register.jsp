<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <script type="text/javascript" src="resources/js/register.js"></script>
    <title>Register</title>
</head>

<body>
    <header>
        <h1 id="message-field">${message}</h1>
    </header>
    <div class="form-div">
        <form id="myForm" name="register" action="register" onsubmit="return validateRegisterForm();" method="Post">
            <div class="input-div">
                <h2 id="h2">Registration</h2>Name:<br>
                <input type="text" name="name" class="form-el" id="name"><br>E-mail:<br>
                <input type="email" name="email" class="form-el" id="email"><br>Password:<br>
                <input type=password name="password" class="form-el" id="password"><br>Password Again:<br>
                <input type="password" name="password-again" class="form-el" id="password-again"><br>
                <input type="radio" name="type" value="Mentor">Mentor<br>
                <input type="radio" name="type" value="Student" class="student-el" checked>Student<br>
                <input id="submit" type="submit" value="Register" class="btn">
            </div>
        </form>
    </div>
</body>
