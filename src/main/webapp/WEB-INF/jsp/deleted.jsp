<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleted</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/delete/${id}" method="post">
    <h1>It was successfully deleted</h1>
    <div>${message}</div>
    <br/>
    <br/>
    <ul>
        <li><a href="/">Main</a></li>
    </ul>
</form>
</body>
</html>
