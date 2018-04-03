<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
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
        </ul>
    </div>


    <div class="main">
        <div class="middle">
            <div class="center">
                <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                    <div class="buttons">
                        <br>
                        <form action="create" method="GET">
                            <input name="type" type=hidden value="text">
                            <input type=submit value="Create Text Page" class="button">
                        </form>
                        <br>
                        <form action="create" method="GET">
                            <input name="type" type=hidden value="assignment">
                            <input type="submit" value="Create Assignment Page" class="button">
                        </form>
                    </div>
                </c:if>


                <c:forEach items="${pages}" var="page">

                    <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                        <table class="pages-table">
                            <tr>
                                <td class="icon-col">
                                    <c:if test="${page.published == true}">
                                        <img class="icon" width="20px" height="20px" src="resources/icons/Tick.png">
                                    </c:if>
                                    <c:if test="${page.published == false}">
                                        <img class="icon" width="20px" height="20px" src="resources/icons/X.png">
                                    </c:if>
                                </td>
                                <td class="type-col">
                                <c:if test="${page.getClass().name == 'com.codecool.lms.model.TextPage'}">
                                    <img class="icon" width="15px" height="20px" src="resources/icons/TextPage.png">
                                </c:if>
                                <c:if test="${page.getClass().name == 'com.codecool.lms.model.AssignmentPage'}">
                                    <img class="icon" width="15px" height="20px" src="resources/icons/AssignmentPage.png">
                                </c:if>
                                </td>
                                <td class="title-col">
                                    <form action="showpage" method="Get">
                                        <input name="title" type="submit" value="${page.title}" class="name">
                                    </form>
                                </td>
                                <c:if test="${page.published == true}">
                                    <td class="button-col">
                                        <form action="showpage" method="Post">
                                            <input name="title" type=hidden value="${page.title}">
                                            <input name="published" type="hidden" value="true">
                                            <input type="submit" value="Unpublish" class="unpublish">
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${page.published == false}">
                                    <td class="button-col">
                                        <form action="showpage" method="Post">
                                            <input name="title" type=hidden value="${page.title}">
                                            <input name="published" type="hidden" value="false">
                                            <input type=submit value="Publish" class="publish">
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                        <c:if test="${page.published == true}">
                            <table class="pages-table">
                                <tr>
                                    <td class="type-col">
                                        <c:if test="${page.getClass().name == 'com.codecool.lms.model.TextPage'}">
                                            <img class="icon" width="15px" height="20px" src="resources/icons/TextPage.png">
                                        </c:if>
                                        <c:if test="${page.getClass().name == 'com.codecool.lms.model.AssignmentPage'}">
                                            <img class="icon" width="15px" height="20px" src="resources/icons/AssignmentPage.png">
                                        </c:if>
                                    </td>
                                    <td class="title-col">
                                        <form action="showpage" method="Get">
                                            <input name="title" type="submit" value="${page.title}" class="name">
                                        </form>
                                    </td>
                                    <td class="grade-col">
                                    <c:if test="${page.getClass().name == 'com.codecool.lms.model.AssignmentPage'}">
                                        ${page.maxScore}
                                    </c:if>
                                    </td>
                                </tr>
                            </table>
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

    <footer>
        <h4>2018</h4>
    </footer>

</body>

</html>
