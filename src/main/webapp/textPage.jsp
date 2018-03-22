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
            <h1 class="head"><a class="ex1" href="redirectHome.jsp">Canvas 2.0</a></h1>
        </nav>
    </header>

    <form action="logout" method="GET" class="logoutform">
        <input type="image" src="resources/icons/logout.png" class="logout" width="50" height="50" alt="Logout">
    </form>

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
                    <h1>Curriculum</h1>
                    <br>
                    <h2>${page.title}</h2>
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
