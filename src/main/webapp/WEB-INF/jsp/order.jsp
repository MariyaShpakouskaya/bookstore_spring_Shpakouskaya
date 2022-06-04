<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<h1>Order, id=${order.id}</h1>
<br/>
<div>User: ${order.userDto.lastName} ${order.userDto.firstName}</div>
<div>Status is ${order.status.toString().toLowerCase()}</div>
<div>Date: ${order.timestamp}</div>
<div>Price = ${order.totalCost} byn</div>
<br/>
<table id="t1" width="100%">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Detail</th>
    </tr>
    <c:forEach items="${items}" var="orderItem">
        <tr>
            <td>${orderItem.bookDto.title}</td>
            <td>${orderItem.bookDto.author}</td>
            <td>${orderItem.bookDto.price} byn</td>
            <td>
                <form action="/books/${orderItem.bookDto.id}" method="get">
                    <input type="submit" value="View book"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<ul>
    <li>
        <a href="/orders">All orders</a>
    </li>
    <br/>
    <br/>
    <li>
        <a href="/">Main</a>
    </li>
</ul>
</body>
</html>
