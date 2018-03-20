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

    <table class = "usersTable">
        <tr>
            <td>Name</td>
            <td>E-mail address</td>
            <td>Role</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td>
                    <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Student'}">
                       Student
                    </c:if>
                    <c:if test = "${user.getClass().name == 'com.codecool.lms.model.Mentor'}">
                        Mentor
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>