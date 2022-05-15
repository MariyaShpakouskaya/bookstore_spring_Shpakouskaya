package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;

public class BookCommand implements Command {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();

    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        BookDto bookDto = BOOK_SERVICE.getBookById(id);
        if (bookDto == null) {
            request.setAttribute("message", "No book with id: " + id);
            return "jsp/error.jsp";
        }
        request.setAttribute("book", bookDto);
        return "jsp/book.jsp";
    }
}
