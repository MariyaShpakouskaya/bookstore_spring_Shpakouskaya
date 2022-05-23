<%@ page import="com.belhard.bookstore.service.impl.UserServiceImpl" %>
<%@ page import="com.belhard.bookstore.service.dto.UserDto" %>
<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Users</title>
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <h1>Users</h1>
        <table id="t1" width="100%">
            <tr>
                <c:if test="${no}">
                    <th>No</th>
                </c:if>
                <th>id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Password</th>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="counter">
                <tr>
                    <c:if test="${no}">
                        <td>${counter.count}</td>
                    </c:if>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role.toString().toLowerCase()}</td>
                    <td>${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>