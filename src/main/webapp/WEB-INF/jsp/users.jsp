<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Users</title>
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <h1>Users</h1>
        <table id="t1" width="100%">
            <tr>
                <th>id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>View</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <form action="/users/${user.id}" method="get">
                            <input type="submit" value="View"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <ul>
            <li>
                <a href="/">Main</a>
            </li>
        </ul>
    </body>
</html>