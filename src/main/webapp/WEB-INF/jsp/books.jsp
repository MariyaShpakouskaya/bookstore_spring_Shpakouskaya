<%@ page import="com.belhard.bookstore.service.impl.BookServiceImpl" %>
<%@ page import="com.belhard.bookstore.service.dto.BookDto" %>
<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Books</title>
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <h1>Books</h1>
        <table id="t1" width="100%">
            <tr>
                <c:if test="${no}">
                    <th>No</th>
                </c:if>
                <th>id</th>
                <th>Title</th>
                <th>Author</th>
                <th>View</th>
            </tr>
            <c:forEach items="${books}" var="book" varStatus="counter">
                <tr>
                    <c:if test="${no}">
                        <td>${counter.count}</td>
                    </c:if>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>
                        <form action="/books/${book.id}" method="get">
                            <input type="submit" value="View"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>