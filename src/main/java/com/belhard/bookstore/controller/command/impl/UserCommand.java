package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.UserServiceImpl;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public class UserCommand implements Command {
    private static final UserService USER_SERVICE = new UserServiceImpl();

    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        UserDto userDto = USER_SERVICE.getUserById(id);
        if (userDto == null) {
            request.setAttribute("message", "No user with id: " + id);
            return "jsp/error.jsp";
        }
        request.setAttribute("user", userDto);
        return "jsp/user.jsp";
    }
}
