package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("users")
public class UsersCommand implements Command {
    private UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute(HttpServletRequest request) {
        Boolean withNo = Boolean.valueOf(request.getParameter("no"));
        List<UserDto> userDtos = userService.getAllUsers();
        request.setAttribute("users", userDtos);
        request.setAttribute("no", withNo);
        return "jsp/users.jsp";
    }
}
