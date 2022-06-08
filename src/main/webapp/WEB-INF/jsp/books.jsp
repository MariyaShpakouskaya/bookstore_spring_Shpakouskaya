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
                <th>id</th>
                <th>Title</th>
                <th>Author</th>
                <th>View</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
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
        <br/>
        <ul>
            <li>
                <c:if test="${page>1}">
                    <a href="/books?page=${page-1}">Previous page</a>
                </c:if>
                <c:if test="${page<=pages}">
                    <a style="padding:5px" href="/books?page=${page+1}">Next page</a>
                </c:if>
            </li>
        </ul>
        <ul>
            <li>
                <a href="/">Main</a>
            </li>
        </ul>
    </body>
</html>