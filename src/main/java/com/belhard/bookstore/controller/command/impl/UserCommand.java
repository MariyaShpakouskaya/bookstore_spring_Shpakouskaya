package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("user")
public class UserCommand implements Command {
    private UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            request.setAttribute("message", "No user with id: " + id);
            return "jsp/error.jsp";
        }
        request.setAttribute("user", userDto);
        return "jsp/user.jsp";
    }
}