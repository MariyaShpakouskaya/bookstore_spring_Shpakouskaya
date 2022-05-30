<%@ page import="com.belhard.bookstore.service.impl.BookServiceImpl" %>
<%@ page import="com.belhard.bookstore.service.dto.BookDto" %>
<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Book</title>
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <h1>Book, id=${book.id}</h1>
        <div>${book.title} by ${book.author}</div>
        <div>${book.cover.toString().toLowerCase()} cover</div>
        <div>ISBN = ${book.isbn}</div>
        <div>Price = ${book.price} byn</div>
        <br/>
        <td>
            <form action="/books/edit/${book.id}" method="get">
                <input type="submit" value="Edit"/>
            </form>
        </td>
        <td>
            <form action="/books/delete/${book.id}" method="post">
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </body>
</html>