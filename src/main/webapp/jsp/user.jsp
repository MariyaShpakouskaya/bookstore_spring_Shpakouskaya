<%@ page import="com.belhard.bookstore.service.UserServiceImpl" %>
<%@ page import="com.belhard.bookstore.service.dto.UserDto" %>
<%@ page contentType="text.html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>User</title>
      <link rel="stylesheet" href="css/style.css"
   </head>
   <body>
      <h1>${user.firstName} ${user.lastName}</h1>
      <div>Email: ${user.email}</div>
      <div>Role: ${user.role.toString().toLowerCase()}</div>
      <div>Password: ${user.password}</div>
   </body>
</html>