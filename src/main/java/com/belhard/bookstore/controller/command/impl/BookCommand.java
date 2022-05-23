package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/books")
public class BookCommand {
    private final BookService bookService;

    @Autowired
    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<BookDto> bookDtos = bookService.getAllBooks();
        model.addAttribute("book", bookDtos);
        return "books";
    }

//    public String execute(HttpServletRequest request) {
//        Long id = Long.valueOf(request.getParameter("id"));
//        BookDto bookDto = bookService.getBookById(id);
//        if (bookDto == null) {
//            request.setAttribute("message", "No book with id: " + id);
//            return "jsp/error.jsp";
//        }
//        request.setAttribute("book", bookDto);
//        return "jsp/book.jsp";
//    }
}
