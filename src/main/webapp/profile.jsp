<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    <title>Profile</title>
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
            <h2>Name: <c:out value="${user.name}" /></h2>
            <h2>E-mail address: <c:out value="${user.email}" /></h2>
            <h2>Role:
                <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Student'}">
                    Student
                </c:if>
                <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
                    Mentor
                </c:if>
            </h2>
                <div>
                    <br>
                        <a href="changeProfile.jsp" class="button">Change your profile</a>
                    <br>
                </div>
        </div>
    </div>


    <footer>
        <h4>2018</h4>
    </footer>

</body>
</html>