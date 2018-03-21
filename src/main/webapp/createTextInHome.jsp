<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" type="text/css" href="resources/css/createtext.css">
    <title>Create Text</title>
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
                    <li><a href=""><img src="resources/icons/titleIcon.png" width="50" height="50"></a></li>
                    <li><a href="#"><img src="resources/icons/documentIcon.png" width="50" height="50"></a></li>
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
                        <div class="container">
                                <h2>New post</h2>
                        </div>
                        <form method="post" action="create" class="w3-container">
                            <h3>Title</h3><br>
                            <input class="title" type="text" class="title">
                            <br>
                            <h3>Content</h3><br>
                            <textarea class="textarea" name="content" class="content"></textarea>
                            <br><br>
                            <input class=button type="submit" value="Submit">
                        </form>
    </div>


<div class="bg"></div>


<footer>
        <h4>2018</h4>
</footer>

</body>
</html>