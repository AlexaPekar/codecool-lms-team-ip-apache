<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/page.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
    <title>Grade statistics</title>
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
            <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                <li>
                    <a>
                        <form action="gradeStatistics" method="GET">
                            <input type="image" src="resources/icons/statistics.png" width="50" height="50" alt="Users" />
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
            <div class="text">
                <h1>Grade statistics</h1>
            </div>
            <div class="table-div">
                <table class="grading-table">

                    <tr>
                        <th class="grading-title-column">
                            Assignment
                        </th>
                        <th class="grading-title-column">
                            Date
                        </th>
                        <th class="grading-title-column">
                            Max score
                        </th>
                        <th class="grading-title-column">
                            Your score
                        </th>
                    </tr>

                </table>

                <c:forEach var="assignment" items="${userAssignments}">
                    <table class="grading-table">
                        <tr>
                            <td class="grading-column">
                                <c:out value="${assignment.title}" />
                            </td>
                            <td class="grading-column">
                                <c:out value="${assignment.date}" />
                            </td>
                            <td class="grading-column">
                                <c:out value="${assignment.maxScore}"/>
                            </td>
                            <td class="grading-column">
                                <c:out value="${assignment.grade}"/>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
                <img src="data:image/png;base64,<c:out value="${b64}"/>">
            </div>
        </div>
    </div>
</div>


<footer>
    <h4>2018</h4>
</footer>

</body>

</html>
