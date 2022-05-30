<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit book</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<form action="/books/${book.id}" method="post">
    <label for="isbn">ISBN: </label>
    <input id="isbn" name="isbn" type="text" value="${book.isbn}"/>
    <br/>
    <label for="author">Author: </label>
    <input id="author" name="author" type="text" value="${book.author}"/>
    <br/>
    <label for="title">Title: </label>
    <input id="title" name="title" type="text" value="${book.title}"/>
    <br/>
    <label for="cover">Cover: </label>
    <input id="cover" name="cover" type="text" value="${book.cover.toString().toUpperCase()}"/>
    <br/>
    <label for="price">Price: </label>
    <input id="price" name="price" type="text" value="${book.price}"/>
    <br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
