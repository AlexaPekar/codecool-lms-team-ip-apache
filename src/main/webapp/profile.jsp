<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
            <link rel="stylesheet" type="text/css" href="resources/css/bootswatch4.css">
            <link rel="shortcut icon" href="resources/icons/titleIcon.png" />
            <title>Profile</title>
        </head>

        <body background="resources/css/img/red2.jpg">

            <header>
                <nav class="ij-effect-1">
                    <h1 class="head"><a class="ex1" href="home?">Canvas 2.0</a></h1>
                </nav>
            </header>
            <table class="user-table">
                <tr>
                    <td class="user-name">${user.name}</td>
                    <td class="user-role">
                        <c:if test="${user.getClass().name == 'com.codecool.lms.model.Student'}">
                            Role: Student
                        </c:if>
                        <c:if test="${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
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
                    <c:if test="${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
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
                    <c:if test="${user.getClass().name == 'com.codecool.lms.model.Student'}">
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
                        <br><br><br><br>
                        <img src="resources/icons/profilepic.png">
                        <h2>Name:
                            <c:out value="${user.name}" />
                        </h2>
                        <h2>E-mail address:
                            <c:out value="${user.email}" />
                        </h2>
                        <h2>Role:
                            <c:if test="${user.getClass().name == 'com.codecool.lms.model.Student'}">
                                Student
                            </c:if>
                            <c:if test="${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
                                Mentor
                            </c:if>
                        </h2>
                        <div>
                            <br>
                            <form action="users" method="POST">
                                <button class="button">Change your profile</button>
                            </form>
                            <br>
                            <br>
                        </div>
                        <div class="container searchContainer">
                            <c:if test="${user.connected == false}">
                                <div class="search card card-body">
                                    <h1>Connect to Github</h1>
                                    <input type="text" id="searchUser" class="form-control" placeholder="GitHub Username...">
                                    <br>
                                </div>
                            </c:if>
                            <br>
                            <div id="profile"></div>
                            <c:if test="${user.connected == true}">
                                <div class="card card-body mb-3">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img name="avatar" class="img-fluid mb-2" src="${github.avatar}">
                                            <a name="html" href="${github.html}" target="_blank" class="btn btn-primary btn-block mb-4">View Profile</a>
                                        </div>
                                        <div class="col-md-9">
                                            <span name="repos" class="badge badge-primary">Public Repos: ${github.repos}</span>
                                            <span name="gists" class="badge badge-secondary">Public Gists: ${github.gists}</span>
                                            <span name="followers" class="badge badge-success">Followers: ${github.followers}</span>
                                            <span name="following" class="badge badge-info">Following: ${github.following}</span>
                                            <br><br>
                                            <ul class="list-group">
                                                <li name="company" class="list-group-item">Company: ${github.company}</li>
                                                <li name="blog" class="list-group-item">Website/Blog: ${github.blog}</li>
                                                <li name="location" class="list-group-item">Location: ${github.location}</li>
                                                <li name="created" class="list-group-item">Member Since: ${github.created}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <h3 class="page-heading mb-3">Latest Repos</h3>
                                <div id="repos"></div>
                                <c:forEach var = "repo" items="${repos}">
                                <div class="card card-body mb-2">
                                <div class="row">
                                <div class="col-md-6">
                                  <a href="${repo.html}" target="_blank">${repo.name}</a>
                                </div>
                                <div class="col-md-6">
                                <span class="badge badge-primary">Stars: ${repo.stars}</span>
                                <span class="badge badge-secondary">Watchers: ${repo.watchers}</span>
                                 <span class="badge badge-success">Forks: ${repo.forks}</span>
                            </div>
                              </div>
                                </div>
                                </c:forEach>
                                <form action="github" method="GET">
                                    <input type="submit" value="Disconnect" class="button">
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            
            


            <footer>
                <h4>2018</h4>
            </footer>

            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

            <script src="resources/js/github.js"></script>
            <script src="resources/js/ui.js"></script>
            <script src="resources/js/app.js"></script>

        </body>

        </html>
