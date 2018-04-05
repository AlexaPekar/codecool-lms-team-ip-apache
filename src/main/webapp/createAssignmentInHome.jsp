<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/create.css">
    <link rel="shortcut icon" href="resources/icons/titleIcon.png"/>
    <title>Create Assignment</title>
</head>

<body background="resources/css/img/red2.jpg">

<header>
    <nav class="ij-effect-1">
        <h1 class="head"><a class="ex1" href="home?">Canvas 2.0</a></h1>
    </nav>
</header>

<table class="user-table">
    <tr>
        <td class="user-name">${current.name}</td>
        <td class="user-role">
            <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                Role: Student
            </c:if>
            <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                Role: Mentor
            </c:if>
        </td>
        <td class="logout-row">
            <form action="logout" method="GET">
                <input type="image" src="resources/icons/logout.png" class="logout" width="50" height="50" alt="Logout">
            </form>
    </tr>
</table>


<div class="column left">
    <nav>
        <ul class="left-ul">
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
            <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                <li>
                    <a>
                        <form action="grading" method="GET">
                            <input type="image" src="resources/icons/grading.png" width="50" height="50" alt="Users"/>
                        </form>
                    </a>
                </li>
                <li>
                    <a>
                        <form action="attendance" method="GET">
                            <input type="image" src="resources/icons/attendance.png" width="50" height="50"
                                   alt="Users"/>
                        </form>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>


<div class="main">
    <div class="column middle">
        <div class="center">
            <div class="container">
                <h2>New Assignment page</h2>
            </div>
            <form method="post" action="create" class="container2">
                <input type=hidden name="type" value="assignment">
                <h3>Title</h3>
                <input name="title" type="text" class="title">
                <br>
                <h3>Content</h3>
                <textarea class="textarea" name="content" class="content"></textarea>
                <br>
                <h3>Max Score</h3>
                <input name="maxScore" type="number" class="maxScore">
                <br>
                <input class=button type="submit" value="Submit">
            </form>
        </div>
    </div>
</div>


<footer>
    <h4>2018</h4>
</footer>

</body>

</html>
