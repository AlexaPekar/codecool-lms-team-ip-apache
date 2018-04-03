<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/page.css" />
    <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
    <title>Text Page</title>
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
            </ul>
        </nav>
    </div>


    <div class="main">
        <div class="middle">
            <div class="center">
                <div class="text">
                    <h1>Text Page</h1>
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
                        <br>
                    <p>${page.content}</p>
                </div>
            </div>
        </div>
    </div>


    <footer>
        <h4>2018</h4>
    </footer>

</body>

</html>
