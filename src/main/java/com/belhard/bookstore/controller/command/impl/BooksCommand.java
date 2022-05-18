package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("books")
public class BooksCommand implements Command {
    private BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public String execute(HttpServletRequest request) {
        Boolean withNo = Boolean.valueOf(request.getParameter("no"));
        List<BookDto> bookDtos = bookService.getAllBooks();
        request.setAttribute("books", bookDtos);
        request.setAttribute("no", withNo);
        return "jsp/books.jsp";
    }
}
