
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/page.css" />
    <title>Home</title>
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
    </div>


    <div class="main">
        <div class="middle">
            <div class="center">

                <div class="text" action="showpage" method="GET">
                    <h1>Assignment</h1>
                    <br>
                    <h2>${page.title}</h2>
                    <p class="maxScore">Max score: ${page.maxScore}</p>
                    <p>${page.content}</p>
                </div>
                <c:if test="${current.getClass().name == 'com.codecool.lms.model.Student'}">
                    <c:if test="${userAlreadySubmitted == false}">


                        <form action="submission" method="GET" class="container2">
                            <textarea name="answer" form="submission-form" class="text-box"></textarea>
                            <input name="pageTitle" type="hidden" value="${page.title}"><br>
                            <input name="title" type="submit" value="Submit" class="submit-button">
                        </form>


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
