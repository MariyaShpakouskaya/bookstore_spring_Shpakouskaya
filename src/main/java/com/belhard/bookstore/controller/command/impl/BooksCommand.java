package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BooksCommand implements Command {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();

    public String execute(HttpServletRequest request) {
        Boolean withNo = Boolean.valueOf(request.getParameter("no"));
        List<BookDto> bookDtos = BOOK_SERVICE.getAllBooks();
        request.setAttribute("books", bookDtos);
        request.setAttribute("no", withNo);
        return "jsp/books.jsp";
    }
}
