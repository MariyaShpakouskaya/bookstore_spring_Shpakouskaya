package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.UserServiceImpl;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        List<UserDto> userDtos = USER_SERVICE.getAllUsers();
        out.write(("<style>*{text-align: center; color: Purple; background-color: #BC8F8F;font-size: 20px}</style>"));
        out.write(("<h1>USERS</h1>"));
        out.write(("<table id=\"t1\" width=\"90%\">"));
        out.write("<tr><th>id</th><th>First name</th><th>Last name</th><th>Email</th><th>Role</th><th>Password</th>");
        for (UserDto userDto : userDtos) {
            out.write("<tr><td>" + userDto.getId() + "</td>");
            out.write("<td>" + userDto.getFirstName() + "</td>");
            out.write("<td>" + userDto.getLastName() + "</td>");
            out.write("<td>" + userDto.getEmail() + "</td>");
            out.write("<td>" + userDto.getRole().toString().toLowerCase() + "</td>");
            out.write("<td>" + userDto.getPassword() + "</td></tr>");
        }
        out.write("</table>");

    }

}
