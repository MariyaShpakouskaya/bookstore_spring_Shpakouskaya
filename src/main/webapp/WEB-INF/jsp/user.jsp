<%@ page import="com.belhard.bookstore.service.impl.UserServiceImpl" %>
<%@ page import="com.belhard.bookstore.service.dto.UserDto" %>
<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>User</title>
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <h1>${user.firstName} ${user.lastName}</h1>
        <div>Email: ${user.email}</div>
        <div>Role: ${user.role.toString().toLowerCase()}</div>
        <div>Password: ${user.password}</div>
        <td>
            <form action="/users/edit/${user.id}" method="get">
                <input type="submit" value="Edit"/>
            </form>
        </td>
        <td>
            <form action="/users/delete/${user.id}" method="post">
                <input type="submit" value="Delete"/>
            </form>
        </td>
        <br/>
        <br/>
        <ul>
            <li>
                <a href="/users">All users</a>
            </li>
            <br/>
            <br/>
            <li>
                <a href="/">Main</a>
            </li>
        </ul>
    </body>
</html>