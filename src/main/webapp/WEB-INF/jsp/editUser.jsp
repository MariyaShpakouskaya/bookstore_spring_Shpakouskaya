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
    <select id="role" name="role" required="required">
        <option value="${user.role.toString()}"> now is ${user.role.toString().toLowerCase()}</option>
        <option value="customer"> customer</option>
        <option value="manager"> manager</option>
        <option value="admin"> admin</option>
    </select>
    <br/>
    <label for="password">Password: </label>
    <input id="password" name="password" type="text" value="${user.password}"/>
    <br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
