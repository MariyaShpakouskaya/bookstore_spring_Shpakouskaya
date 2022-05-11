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

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        UserDto userDto = USER_SERVICE.getUserById(id);
        if (userDto == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.write(("<style>*{text-align: center; line-height: 2; color: DarkBlue; background-color: #00FFFF;font-size: 25px}</style>"));
        out.write(("<h1>USER</h1>"));
        out.write(("<div>" + userDto.getFirstName() + " " + userDto.getLastName() + "</div>"));
        out.write(("<div>Email: " + userDto.getEmail() + "</div>"));
        out.write(("<div>Role: " + userDto.getRole().toString().toLowerCase() + "</div>"));
        out.write(("<div>Password: " + userDto.getPassword() + "</div>"));
    }
}
