<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/users.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/attendance.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
    <title>Attendance</title>
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
        <td class = "user-role">
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

<div class="left">
    <nav>
        <ul class="left-ul">
            <li>
                <a>
                    <form action="profile" method="GET">
                        <input type="image" src="resources/icons/titleIcon.png" width="50" height="50" alt="Profile" />
                    </form>
                </a>
            </li>
            <li>
                <a>
                    <form action="home" method="GET">
                        <input type="image" src="resources/icons/documentIcon.png" width="50" height="50" alt="Home" />
                    </form>
                </a>
            </li>
            <li>
                <a>
                    <form action="users" method="GET">
                        <input type="image" src="resources/icons/peoplesIcon.png" width="50" height="50" alt="Users" />
                    </form>
                </a>
            </li>
            <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                <li>
                    <a>
                        <form action="grading" method="GET">
                            <input type="image" src="resources/icons/grading.png" width="50" height="50" alt="Users" />
                        </form>
                    </a>
                </li>
                <li>
                    <a>
                        <form action="attendance" method="GET">
                            <input type="image" src="resources/icons/attendance.png" width="50" height="50" alt="Users" />
                        </form>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>


<div class="main">
    <div class="middle">
        <div class="center">
            <h1 class="page-title">Students:</h1>

            <form action="attendance" method="POST">
            <input type=date name="attendanceDate" max="${currentDate}" value="${attendanceDate}" required>
            <br><br><br>
            <input type=submit Value="Select" class="button">
            <br>
            </form>

            <div class="table-div">
             <form action="attend" method="POST">
                <table align="center" class="users-table">
                    <tr class="head-row">
                        <td class="head-cell">Name</td>
                        <td> </td>
                    </tr>

                        <input type=hidden name="attendance" value="${attendanceDate}">
                        <c:forEach var="user" items="${users}">
                            <c:set var="contains" value="false" />
                            <c:forEach var = "userhere" items="${here}">
                                <c:if test="${user.name == userhere.name}">
                                    <c:set var="contains" value="true" />
                                </c:if>
                            </c:forEach>
                                <tr class="text-row">
                                <td>
                                    <c:out value="${user.name}" />
                                </td>
                                <td> </td>
                                <td>
                                <label class="container">
                                <c:if test="${contains == true}">
                                    <input type="checkbox" name="selected" value="${user.name}" checked class=>
                                    <span class="checkmark"></span>
                                </c:if>
                                <c:if test="${contains == false}">
                                    <input type="checkbox" name="selected" value="${user.name}" class=>
                                    <span class="checkmark"></span>
                                </c:if>
                                </label>
                                </td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br><br><br>
                    <input type=submit name=submit Value="Save" class="button" align: bottom>
                </form>
            </div>
        </div>
    </div>
</div>


<footer>
    <h4>2018</h4>
</footer>

</body>

</html>
