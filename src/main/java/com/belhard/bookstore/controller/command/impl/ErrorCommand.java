package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

    public String execute(HttpServletRequest request) {
        request.setAttribute("message", "Ooops");
        return "jsp/error.jsp";
    }
}
