<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/page.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
    <title>Grading</title>
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
            <div class="text">
                <h1>Assignments</h1>
            </div>
            <div class="table-div">
                <table class="grading-table">

                    <tr>
                        <th class="grading-title-column">
                            Name
                        </th>
                        <th class="grading-title-column">
                            Assignment
                        </th>
                        <th class="grading-title-column">
                            Submission
                        </th>
                        <th class="grading-title-column">
                            Grade
                        </th>
                        <th class="grading-title-column"></th>
                        <th class="grading-title-column"></th>
                    </tr>

                </table>

                <c:forEach var="assignment" items="${assignments}">
                    <table class="grading-table">
                        <tr>
                            <td class="grading-column">
                                <c:out value="${assignment.student.name}" />
                            </td>
                            <td class="grading-column">
                                <c:out value="${assignment.title}" />
                            </td>
                            <td class="grading-column">
                                <c:out value="${assignment.answer}"/>
                            </td>
                            <form action="grading" method="POST">
                                <td class="grading-column">
                                    <input type="number" name="grade" value="${assignment.grade}">
                                    <input type="hidden" name="student" value="${assignment.student.name}">
                                    <input type="hidden" name="title" value="${assignment.title}">
                                </td>
                                <td class="grading-column">
                                    &#47; ${assignment.maxScore}
                                </td>
                                <td class="grading-column">
                                    <input type="submit" value="Grade">
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:forEach>

            </div>
        </div>
    </div>
</div>


<footer>
    <h4>2018</h4>
</footer>

</body>

</html>
