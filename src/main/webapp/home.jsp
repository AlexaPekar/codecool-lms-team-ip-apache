<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <link rel="stylesheet" type="text/css" href="resources/css/img/home2.css" />
            <link type="shortcut" href="resources/icons/titleIcon.png" />

            <title>Home</title>
        </head>

        <body>

            <header>
                <nav class="ij-effect-1">
                    <h1 class="head"><a class="ex1" href="home.jsp">Canvas 2.0</a></h1>
                </nav>
            </header>




            <div class="row">
                <div class="column left" style="background-color:#d30000">
                    <nav>
                        <div class="vertical-menu">
                            <ul>

                                <li><a href="home"><img src="resources/icons/titleIcon.png" width="50" height="50"></a></li>
                                <li><a href="#"><img src="resources/icons/documentIcon.png" width="50" height="50"></a></li>
                                <li>
                                    <form action="users" method="GET">
                                        <input type="hidden" name="kecske" value="600" />
                                        <input type="image" src="resources/icons/peoplesIcon.png" width="50" height="50" alt="Users" />
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>

                <div class="column middle" style="background-color:white;">
                    <div class="center">
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
                    <br>
                    <div class="center">
                        <h2>Text page section</h2>
                        <b>this is a test.</b>
                        <p>test text content.</p>
                        <h5>Ip-Apache</h5>
                    </div>
                </div>


                <div class="column right" style="background-color:#d30000;">
                    <nav>
                        <div class="vertical-menu right-menu">
                            <ul>
                                <h2>Side page</h2>
                                <b>News</b>
                                <p>ads</p>
                                <h5>Ip-Apache</h5>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>

            <footer>
                <nav class="ij-effect-1">
                    <h1 class="head">2018</h1>
                </nav>
            </footer>

        </body>

        </html>
