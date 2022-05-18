package com.belhard.bookstore.controller;

import com.belhard.bookstore.ContextConfiguration;
import com.belhard.bookstore.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
    }

    @Override
    public void destroy() {
        context.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("command");
        Command command = (Command) context.getBean(action);
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
