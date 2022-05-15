package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.UserServiceImpl;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class UsersCommand implements Command {
    private static final UserService USER_SERVICE = new UserServiceImpl();

    public String execute(HttpServletRequest request) {
        Boolean withNo = Boolean.valueOf(request.getParameter("no"));
        List<UserDto> userDtos = USER_SERVICE.getAllUsers();
        request.setAttribute("users", userDtos);
        request.setAttribute("no", withNo);
        return "jsp/users.jsp";
    }
}
