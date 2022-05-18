package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller("book")
public class BookCommand implements Command {
    private BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        BookDto bookDto = bookService.getBookById(id);
        if (bookDto == null) {
            request.setAttribute("message", "No book with id: " + id);
            return "jsp/error.jsp";
        }
        request.setAttribute("book", bookDto);
        return "jsp/book.jsp";
    }
}
