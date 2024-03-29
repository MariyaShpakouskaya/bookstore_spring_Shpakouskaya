<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<h1>Books</h1>
<table id="t1" width="100%">
    <tr>
        <th>id</th>
        <th>User</th>
        <th>Total cost</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.userDto.lastName} ${order.userDto.firstName}</td>
            <td>${order.totalCost} byn</td>
            <td>
                <form action="/orders/${order.id}" method="get">
                    <input type="submit" value="View"/>
                </form>
            </td>
            <td>
                <form action="/orders/delete/${order.id}" method="post">
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<ul>
    <li>
        <c:if test="${page>1}">
            <a href="/orders?page=${page-1}">Previous page</a>
        </c:if>
        <c:if test="${page<=pages}">
            <a style="padding:5px" href="/orders?page=${page+1}">Next page</a>
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
