<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="index.css">
    <title>Users</title>
</head>
<body>
<div>

    <p>Users:</p>

    <c:forEach var="user" items="${users}">
        <p><c:out value="${user.name}" /></p>
        <p><c:out value="${user.email}" /></p>
        <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Student'}">
           <p>Student<p>
        </c:if>
        <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
            <p>Mentor<p>
        </c:if>
    </c:forEach>

</div>
</body>
</html>