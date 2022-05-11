package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book")
public class BookController extends HttpServlet {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Long id = Long.valueOf(request.getParameter("id"));
        BookDto bookDto = BOOK_SERVICE.getBookById(id);
        if (bookDto == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("<style>*{text-align: center; color: Red; font-size: 30px}</style>");
            out.write("Book doesn't exist!");
            return;
        }
        response.setStatus(200);
        out.write(("<style>*{text-align: center; color: DarkGreen; background-color: #DB7093;font-size: 16px}</style>"));
        out.write(("<h1>BOOK</h1>"));
        out.write(("<div>" + bookDto.getTitle().toUpperCase() + " by " + bookDto.getAuthor() + "</div>"));
        out.write(("<div>ISBN: " + bookDto.getIsbn() + "</div>"));
        out.write(("<div>Cover: " + bookDto.getCover().toString().toLowerCase() + "</div>"));
        out.write(("<div>Price: " + bookDto.getPrice() + " byn</div>"));

    }
}
