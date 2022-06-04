<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<h1>Order, id=${order.id}</h1>
<div>${order.userDto.getLastName()}</div>
<div>${order.status.toString().toLowerCase()} status</div>
<div>Date: ${order.timestamp}</div>
<div>Price = ${order.totalCost} byn</div>
<br/>
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
