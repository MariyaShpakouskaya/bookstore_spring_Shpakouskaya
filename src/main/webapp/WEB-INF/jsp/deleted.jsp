<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleted</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/books/delete/${book.id}" method="post">
    <h1>It was successfully deleted</h1>
    <div>${message}</div>
    <ul>
        <li><a href="/books">All books</a></li>
        <br/>
        <li><a href="/">Main</a></li>
    </ul>
</form>
</body>
</html>
