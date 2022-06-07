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
        </tr>
    </c:forEach>
</table>
<br/>
<ul>
    <li>
        <a href="/">Main</a>
    </li>
</ul>
</body>
</html>
