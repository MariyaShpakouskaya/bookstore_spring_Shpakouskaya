<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/users/${user.id}" method="post">
    <label for="email">Email: </label>
    <input id="email" name="email" type="text" value="${user.email}"/>
    <br/>
    <label for="first name">First name: </label>
    <input id="first name" name="first_name" type="text" value="${user.firstName}"/>
    <br/>
    <label for="last name">Last name: </label>
    <input id="last name" name="last_name" type="text" value="${user.lastName}"/>
    <br/>
    <label for="role">Role: </label>
    <input id="role" name="role" type="text" value="${user.role.toString().toUpperCase()}"/>
    <br/>
    <label for="password">Password: </label>
    <input id="password" name="password" type="text" value="${user.password}"/>
    <br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
