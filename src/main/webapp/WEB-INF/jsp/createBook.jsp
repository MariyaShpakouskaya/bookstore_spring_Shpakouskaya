<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/books" method="post">
    <label for="isbn">ISBN: </label>
    <input id="isbn" name="isbn" type="text"/>
    <br/>
    <label for="author">Author: </label>
    <input id="author" name="author" type="text"/>
    <br/>
    <label for="title">Title: </label>
    <input id="title" name="title" type="text"/>
    <br/>
    <label for="cover">Cover: </label>
    <input id="cover" name="cover" type="text"/>
    <br/>
    <label for="price">Price: </label>
    <input id="price" name="price" type="text"/>
    <br/>
    <br/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
