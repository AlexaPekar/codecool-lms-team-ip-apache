<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    <title>Home</title>
</head>
<body background="resources/css/img/red2.jpg">

<header>
    <nav class="ij-effect-1">
    <h1 class="head"><a class="ex1" href="home.jsp">Canvas 2.0</a></h1>
    </nav>
</header>

    <div class="column left">
        <nav>
                <ul>
                    <li>
                        <a>
                           <form action="#" method="GET">
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
        </nav>
    </div>


    <div class="main">
    <div class="column middle">
        <div class="center">
                <div class="buttons">
                    <br>
                    <form action="createTextInHome.jsp" method="GET">
                        <input name="type" type=hidden value="text"></input>
                        <input type=submit value="Create Text Page" class="button"></input>
                    </form>
                    <br>
                    <form action="#" method="GET">
                        <input name="type" type=hidden value="assignment"></input>
                        <input type=submit value="Create Assignment Page" class="button"></input>
                    </form>
                </div>

                        <table>
                            <c:forEach items="${pages}" var="page">
                                <tr>
                                    <c:if test="${current.getClass().name == 'com.codecool.lms.model.Mentor'}">
                                        <td>
                                            <c:out value="${page.title}" />
                                        </td>
                                        <c:if test="${page.published == true}">
                                            <td><label name="label">Unpublish</label></td>
                                        </c:if>
                                        <c:if test="${page.published == false}">
                                            <td><label name="label">Publish</label></td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                                        <c:if test="${page.published == true}">
                                            <td>
                                                <c:out value="${page.title}" />
                                            </td>
                                        </c:if>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </table>
        </div>
    </div>


<div class="bg"></div>


<footer>
        <h4>2018</h4>
</footer>

</body>
</html>