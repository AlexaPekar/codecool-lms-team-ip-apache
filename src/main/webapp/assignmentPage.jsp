<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/page.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png"/>
    <title>Home</title>
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
        <ul>
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
    </div>


    <div class="main">
        <div class="middle">
            <div class="center">

                <div class="text">
                    <h1>Assignment</h1>
                    <br>
                    <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                        <div class="buttons">
                            <form action="edit" method="GET">
                                <input name="page" type=hidden value="${page.title}">
                                <input type=submit value="Edit page" class="button">
                            </form>
                            <br>
                            <form action="delete" method="GET">
                                <input name="page" type=hidden value="${page.title}">
                                <input type="submit" value="Delete Page" class="button">
                            </form>
                        </div>
                    </c:if>
                    <h2>${page.title}</h2>
                    <p class="score">Max score: ${page.maxScore}</p>
                    <p>${page.content}</p><br><br>
                </div>
                <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                    <c:if test="${userAlreadySubmitted == false}">

                        <form action="submission" method="GET" class="container2">
                            <textarea name="answer" class="text-box"></textarea>
                            <input name="pageTitle" type="hidden" value="${page.title}"><br>
                            <input name="title" type="submit" value="Submit" class="submit-button">
                        </form>

                    </c:if>

                    <c:if test="${userAlreadySubmitted == true}">

                        <div class="text">
                            <h3>Submission details:</h3>
                            <p class="score">Your score: ${point} &#47; ${page.maxScore}</p>
                            <p>${answer}</p>
                        </div>

                    </c:if>
                </c:if>

            </div>
        </div>
    </div>

    <footer>
        <h4>2018</h4>
    </footer>

</body>

</html>
