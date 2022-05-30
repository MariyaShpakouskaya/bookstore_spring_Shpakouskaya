<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/users" method="post">
    <label for="email">Email: </label>
    <input id="email" name="email" type="text"/>
    <br/>
    <label for="first name">First name: </label>
    <input id="first name" name="first_name" type="text"/>
    <br/>
    <label for="last name">Last name: </label>
    <input id="last name" name="last_name" type="text"/>
    <br/>
    <label for="role">Role: </label>
    <input id="role" name="role" type="text"/>
    <br/>
    <label for="password">Password: </label>
    <input id="password" name="password" type="text"/>
    <br/>
    <br/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
