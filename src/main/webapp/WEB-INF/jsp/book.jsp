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
        <br/>
        <br/>
        <ul>
            <li>
                <a href="/books?page=1">All books</a>
            </li>
            <br/>
            <br/>
            <li>
                <a href="/">Main</a>
            </li>
        </ul>
    </body>
</html>