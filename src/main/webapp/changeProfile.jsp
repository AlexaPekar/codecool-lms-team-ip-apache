<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    <title>Change Your Profile</title>
</head>
<body background="resources/css/img/red2.jpg">

<header>
    <nav class="ij-effect-1">
        <h1 class="head"><a class="ex1" href="home.jsp">Canvas 2.0</a></h1>
    </nav>
</header>

<div class="left">
        <ul>
            <li>
                <a>
                    <form action="profile" method="GET">
                        <input type="image" src="resources/icons/titleIcon.png" width="50" height="50" alt="Profile"/>
                    </form>
                </a>
            </li>
            <li>
                <a>
                    <form action="home" method="GET">
                        <input type="image" src="resources/icons/documentIcon.png" width="50" height="50" alt="Home"/>
                    </form>
                </a>
            </li>
            <li>
                <a>
                    <form action="users" method="GET">
                        <input type="image" src="resources/icons/peoplesIcon.png" width="50" height="50" alt="Users"/>
                    </form>
                </a>
            </li>
        </ul>
</div>


<div class="main">
    <div class="middle">
        <div class="center">
            <br><br>
            <form name="profileChange" method="post" action="users">
                <br>Name<br>
                <input type="text" name="newName">
                <br>Password<br>
                <input type="text" name="newPassword">
                <br><br>
                <input type="submit" value="Submit" class="button">
            </form>
        </div>
    </div>
</div>


    <footer>
        <h4>2018</h4>
    </footer>

</body>
</html>